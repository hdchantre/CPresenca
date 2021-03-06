package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Aluno;
import Model.Chamada;
import Model.Disciplina;
import Model.Turma;

public class AulaDAO {

	MainDAO mainDAO = new MainDAO();
	CommonDAO commonDAO = new CommonDAO();

	Connection connection;

	private static final String DB_VERIFICA_CHAMADA_ABERTA = "select * from chamada where turma = ? and fim_aula = false";
	private static final String DB_INICIALIZA_CHAMADA = "insert into chamada (turma, data_chamada, hora_inicio, fim_aula, posi_x, posi_y, por_presenca,tempo_ticket) values (?,?,?,false,?,?,?,?)";
	private static final String DB_FINALIZA_CHAMADA = "update chamada set fim_aula = true, hora_fim = ? where turma=? and fim_aula = false";
	private static final String DB_GET_TURMA_PROFESSOR = "SELECT t.id, t.disciplina, d.nome FROM turma as t, disciplina as d WHERE datafim > CURRENT_TIMESTAMP and professor = (select id from usuario where usuario = ?) and t.disciplina = d.id";
	private static final String DB_GET_TURMA_ALUNO = "SELECT t.id, t.disciplina, d.nome FROM turma as t, disciplina as d, turma_aluno as ta WHERE t.datafim > CURRENT_TIMESTAMP and ta.aluno = (select id from usuario where usuario = ?) and t.disciplina = d.id and ta.turma = t.id";
	private static final String DB_ALUNO_EM_AULA = "insert into chamada_aluno(aluno_id, chamada_id, in_aula, is_presente) values ((select id from usuario where usuario = ?),?,true, false)";
	private static final String DB_VERIFICA_ALUNO_EM_AULA = "select ca.id from chamada_aluno as ca, chamada as c where aluno_id = (select id from usuario where usuario = ?) and in_aula = true and ca.chamada_id = c.id and c.fim_aula = false";
	private static final String DB_VERIFICA_ALUNO_PRESENTE = "select is_presente from chamada_aluno where aluno_id = (select id from usuario where usuario = ?)  and chamada_id = ?";
	private static final String DB_SAIR_AULA = "update chamada_aluno set in_aula = false where aluno_id = (select id from usuario where usuario = ?)";
	private static final String DB_MARCAR_PRESENCA_ALUNO = "update chamada_aluno set is_presente = ? where aluno_id = ? and chamada_id = ?";
	private static final String DB_SALVAR_TICKET = "insert into ticket (aluno_id, chamada_id, posi_x, posi_y, data_ticket, hora_ticket) values ((select id from usuario where usuario = ?),?,?,?,?,?)";
	private static final String DB_GET_TICKETS = "select t.*, u.nome, u.id as aid from ticket as t, usuario as u where chamada_id in (select id from chamada_aluno where chamada_id = ?) and t.aluno_id = u.id  order by aluno_id";
	private static final String DB_GET_LISTA_ALUNO = "select t.*, u.nome from turma_aluno as t, usuario as u where turma=? and t.aluno = u.id  order by aluno";

	//H4
	public Chamada inicializaChamada(String nomeUsuario, Integer idTurma,
			float posix, float posiy, Integer porpre, Integer tempoTicket) {
		Chamada chamada = new Chamada();
		chamada.setChamadaAberta(false);
		
		String estado = commonDAO.isUsuarioLogado(nomeUsuario);
		boolean isProfessor = commonDAO.isProfessor(nomeUsuario);

		if ("logado".equals(estado) && isProfessor) {
			connection = mainDAO.conectarDB();

			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA);

				ps.setInt(1, idTurma);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					mainDAO.fecharConexaoDB();
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				ps = connection.prepareStatement(DB_INICIALIZA_CHAMADA);

				java.sql.Date data = new java.sql.Date((new Date()).getTime());

				ps.setInt(1, idTurma);
				ps.setDate(2, data);
				ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				ps.setDouble(4, posix);
				ps.setDouble(5, posiy);
				ps.setInt(6, porpre);
				ps.setInt(7, tempoTicket);

				if (ps.executeUpdate() > 0) {
					chamada.setChamadaAberta(true);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			mainDAO.fecharConexaoDB();
		}

		return chamada;
	}

	//H -sem hist�ria
	public List<Turma> getTurmasProfessor(String nomeUsuario) {

		List<Turma> turmas = new ArrayList<Turma>();
		Turma turma;
		Disciplina disciplina;

		String estado = commonDAO.isUsuarioLogado(nomeUsuario);

		if ("logado".equals(estado)) {
			connection = mainDAO.conectarDB();

			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(DB_GET_TURMA_PROFESSOR);

				ps.setString(1, nomeUsuario);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					turma = new Turma();
					disciplina = new Disciplina();

					turma.setId(rs.getInt("id"));
					turma.setChamadaAberta(false);
					disciplina.setId(rs.getInt("disciplina"));
					disciplina.setNome(rs.getString("nome"));

					ps = connection
							.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA);

					ps.setInt(1, rs.getInt("id"));

					ResultSet rs2 = ps.executeQuery();

					if (rs2.next()) {
						turma.setChamadaAberta(true);
					}

					turma.setDisciplina(disciplina);

					turmas.add(turma);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainDAO.fecharConexaoDB();
		} else {
			return null;
		}

		return turmas;
	}

	
	public List<Turma> getTurmasAluno(String nomeUsuario) {

		List<Turma> turmas = new ArrayList<Turma>();
		Turma turma;
		Disciplina disciplina;

		String estado = commonDAO.isUsuarioLogado(nomeUsuario);

		if ("logado".equals(estado)) {
			connection = mainDAO.conectarDB();

			PreparedStatement ps;
			try {
				ps = connection.prepareStatement(DB_GET_TURMA_ALUNO);

				ps.setString(1, nomeUsuario);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					turma = new Turma();
					disciplina = new Disciplina();

					turma.setId(rs.getInt("id"));
					turma.setChamadaAberta(false);
					disciplina.setId(rs.getInt("disciplina"));
					disciplina.setNome(rs.getString("nome"));

					ps = connection
							.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA);

					ps.setInt(1, rs.getInt("id"));

					ResultSet rs2 = ps.executeQuery();

					if (rs2.next()) {
						turma.setChamadaAberta(true);
					}

					turma.setDisciplina(disciplina);

					turmas.add(turma);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainDAO.fecharConexaoDB();
		} else {
			return null;
		}

		return turmas;
	}

	//H5
	public Chamada finalizaChamada(Integer idTurma) {
		Chamada chamada = new Chamada();
		chamada.setChamadaAberta(true);

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA);

			ps.setInt(1, idTurma);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ps = connection.prepareStatement(DB_FINALIZA_CHAMADA);

				ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				ps.setInt(2, idTurma);

				if (ps.executeUpdate() > 0) {
					chamada.setChamadaAberta(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return chamada;
	}

	//H6
	public Chamada checkAluno(String nomeUsuario, Integer idTurma) {
		Chamada chamada = new Chamada();
		chamada.setChamadaAberta(false);

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA);

			ps.setInt(1, idTurma);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ps = connection.prepareStatement(DB_ALUNO_EM_AULA);

				ps.setString(1, nomeUsuario);
				ps.setInt(2, rs.getInt("id"));

				if (ps.executeUpdate() > 0) {
					chamada.setChamadaAberta(true);
					chamada.setId(rs.getInt("id"));
					chamada.setTempoTicket(rs.getInt("tempo_ticket"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return chamada;
	}

	//H - sem historia
	public Chamada checkOutAluno(String nomeUsuario) {
		Chamada chamada = new Chamada();
		chamada.setChamadaAberta(true);

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_SAIR_AULA);

			ps.setString(1, nomeUsuario);

			if (ps.executeUpdate() > 0) {
				chamada.setChamadaAberta(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return chamada;
	}

	//H8
	public Chamada ticketAluno(String nomeUsuario, float posiX, float posiY) {
		Chamada chamada = new Chamada();
		chamada.setChamadaAberta(false);

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VERIFICA_ALUNO_EM_AULA);

			ps.setString(1, nomeUsuario);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ps = connection.prepareStatement(DB_SALVAR_TICKET);

				java.sql.Date data = new java.sql.Date((new Date()).getTime());

				ps.setString(1, nomeUsuario);
				ps.setInt(2, rs.getInt("id"));
				ps.setFloat(3, posiX);
				ps.setFloat(4, posiY);
				ps.setDate(5, data);
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

				ps.executeUpdate();
				chamada.setChamadaAberta(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return chamada;
	}

	//H15 e H17
	public boolean verificarPresencaAluno(Integer chamadaId, String nomeUsuario) {
		boolean isPresente = false;
		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VERIFICA_ALUNO_PRESENTE);

			ps.setString(1, nomeUsuario);
			ps.setInt(2, chamadaId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				isPresente = rs.getBoolean("is_presente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return isPresente;
	}

	//N�o representa uma historia em especifico, mas faz parte de v�rias
	//H8,  h17 e H15
	public Chamada contabiliza(Integer idTurma) {
		Chamada chamada = new Chamada();
		Aluno aluno = new Aluno();
		Map<Integer, String> lista = new HashMap<Integer, String>();
		Map<Aluno, Boolean> lista2 = new HashMap<Aluno, Boolean>();
		Map<Integer, Integer> listaTicket = new HashMap<Integer, Integer>();
		Date dataAula = new Date();
		int idChamada = 0;

		int porPresenca = 75;
		int tempoTicket = 5;

		Timestamp fimAula = new Timestamp(System.currentTimeMillis());
		int fimAulaHoras = fimAula.getHours();
		int fimAulaMinutos = fimAula.getMinutes();

		Timestamp inicioAula = new Timestamp(System.currentTimeMillis());
		int inicioAulaHoras = inicioAula.getHours();
		int inicioAulaMinutos = inicioAula.getMinutes();

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA);

			ps.setInt(1, idTurma);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				porPresenca = rs.getInt("por_presenca");
				tempoTicket = rs.getInt("tempo_ticket");

				inicioAula = rs.getTimestamp("hora_inicio");
				inicioAulaHoras = inicioAula.getHours();
				inicioAulaMinutos = inicioAula.getMinutes();

				ps = connection.prepareStatement(DB_GET_LISTA_ALUNO);

				ps.setInt(1, idTurma);

				ResultSet rs2 = ps.executeQuery();

				while (rs2.next()) {
					lista.put(rs2.getInt("aluno"), rs2.getString("nome"));
					listaTicket.put(rs2.getInt("aluno"), 0);
				}

				aluno = new Aluno();

				ps = connection.prepareStatement(DB_GET_TICKETS);

				ps.setInt(1, rs.getInt("id"));
				idChamada = rs.getInt("id");
				dataAula = rs.getDate("data_chamada");

				ResultSet rs3 = ps.executeQuery();

				while (rs3.next()) {
					
					if (dataAula.equals(rs3.getDate("data_ticket"))) {
						listaTicket.put(rs3.getInt("aid"),
								listaTicket.get(rs3.getInt("aid"))+1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int minDiferente = (60 - inicioAulaMinutos + fimAulaMinutos)
				+ ((fimAulaHoras - inicioAulaHoras) * 60) - 60;
		
		int quantidadeTickets = ((minDiferente / tempoTicket) * porPresenca) / 100;

		for (Integer id : listaTicket.keySet()) {
			aluno = new Aluno();
			aluno.setIdChamada(idChamada);
			aluno.setID(id);
			aluno.setNome(lista.get(id));
			if (listaTicket.get(id) == 0
					|| listaTicket.get(id) < quantidadeTickets) {
				lista2.put(aluno, false);
			} else {
				lista2.put(aluno, true);
			}
		}

		chamada.setAlunos(lista2);
		mainDAO.fecharConexaoDB();

		return chamada;
	}

	//H7 - n�o est� em nenhuma sprint
	public void setPresencaAluno(Map<Aluno, Boolean> lista) {

		connection = mainDAO.conectarDB();

		PreparedStatement ps;

		try {
			for (Aluno aluno : lista.keySet()) {
				ps = connection.prepareStatement(DB_MARCAR_PRESENCA_ALUNO);

				ps.setBoolean(1, lista.get(aluno));
				ps.setInt(2, aluno.getID());
				ps.setInt(3, aluno.getIdChamada());

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();
	}
}
