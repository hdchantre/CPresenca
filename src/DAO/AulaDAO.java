package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Chamada;
import Model.Disciplina;
import Model.Turma;
import XML.InicializaChamada;

public class AulaDAO {

	MainDAO mainDAO = new MainDAO();
	CommonDAO commonDAO = new CommonDAO();

	Connection connection;

	private static final String DB_VERIFICA_CHAMADA_ABERTA = "select * from chamada where turma = ? and fim_aula = false";
	private static final String DB_INICIALIZA_CHAMADA = "insert into chamada (turma, data_chamada, hora_inicio, fim_aula) values (?,?,?,false)";
	private static final String DB_FINALIZA_CHAMADA = "update chamada set fim_aula = true where turma=?";
	private static final String DB_GET_TURMA_PROFESSOR = "SELECT t.id, t.disciplina, d.nome, c.fim_aula FROM turma as t, disciplina as d, chamada as c WHERE datafim > CURRENT_TIMESTAMP and professor = (select id from usuario where usuario = ?) and t.disciplina = d.id and c.turma = t.id";
	private static final String DB_GET_TURMA_ALUNO = "SELECT t.id, t.disciplina, d.nome, c.fim_aula FROM turma as t, disciplina as d, turma_aluno as ta, chamada as c WHERE t.datafim > CURRENT_TIMESTAMP and ta.aluno = (select id from usuario where usuario = ?) and t.disciplina = d.id and ta.turma = t.id and c.turma = t.id";

	public Chamada inicializaChamada(String nomeUsuario, Integer idTurma) {
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
					turma.setChamadaAberta(rs.getBoolean("fim_aula"));
					disciplina.setId(rs.getInt("disciplina"));
					disciplina.setNome(rs.getString("nome"));

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
					turma.setChamadaAberta(rs.getBoolean("fim_aula"));
					disciplina.setId(rs.getInt("disciplina"));
					disciplina.setNome(rs.getString("nome"));

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

				ps.setInt(1, idTurma);

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

}
