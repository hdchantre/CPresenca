package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import XML.InicializaChamada;

public class AulaDAO {

	MainDAO mainDAO = new MainDAO();
	Connection connection;

	private static final String DB_VERIFICA_CHAMADA_ABERTA = "select * from chamada where turma = ? and fim_aula = false";
	private static final String DB_INICIALIZA_CHAMADA = "insert into chamada (turma, data_chamada, hora_inicio, fim_aula) values (?,?,?,false)";
	private static final String DB_VERIFICA_CHAMADA_ABERTA_PROFESSOR = "select c.fim_aula from chamada as c where c.turma in (select t.id from turma as t, usuario as u where t.professor = u.id and u.usuario = ?)";
	private static final String DB_VARIFICA_CHAMADA_ABERTA_ALUNO = "select c.fim_aula from chamada as c where c.turma in (select t.id from turma_aluno as t, usuario as u where t.aluno = u.id and u.usuario = ?)";

	public InicializaChamada inicializaChamada(Integer idTurma) {
		InicializaChamada iChamada = new InicializaChamada();

		iChamada.setInicializada(false);

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA);

			ps.setInt(1, idTurma);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				iChamada.setCausaDoProblema("Chamada em aberto");
				mainDAO.fecharConexaoDB();
				return iChamada;
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

			int i = ps.executeUpdate();

			if (i > 0) {
				iChamada.setInicializada(true);
			}
		} catch (SQLException e) {
			iChamada.setCausaDoProblema("Problema no banco");
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return iChamada;
	}
	
	public boolean verificaChamadaAbertaProfessor(String usuario) {
		
		boolean isAberta = false;

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA_PROFESSOR);

			ps.setString(1, usuario);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if(!rs.getBoolean("fim_aula")){
					isAberta = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mainDAO.fecharConexaoDB();

		return isAberta;
	}
	
	public boolean verificaChamadaAbertaAluno(String usuario) {
		
		boolean isAberta = false;

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_VARIFICA_CHAMADA_ABERTA_ALUNO);

			ps.setString(1, usuario);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if(!rs.getBoolean("fim_aula")){
					isAberta = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mainDAO.fecharConexaoDB();

		return isAberta;
	}

}
