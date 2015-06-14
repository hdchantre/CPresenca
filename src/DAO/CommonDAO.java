package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonDAO {

	MainDAO mainDAO = new MainDAO();

	Connection connection;

	private static final String DB_VERIFICA_USUARIO_LOGADO = "select islogado from usuario where usuario=? and chave=?";
	private static final String DB_VERIFICA_CHAMADA_ABERTA_PROFESSOR = "select c.fim_aula from chamada as c where c.turma in (select t.id from turma as t, usuario as u where t.professor = u.id and u.usuario = ?)";
	private static final String DB_VARIFICA_CHAMADA_ABERTA_ALUNO = "select c.fim_aula from chamada as c where c.turma in (select t.id from turma_aluno as t, usuario as u where t.aluno = u.id and u.usuario = ?)";

	public String isUsuarioLogado(String nomeUsuario, Integer chave) {

		String estado = "Chave Errada";
		PreparedStatement ps;

		connection = mainDAO.conectarDB();

		try {

			ps = connection.prepareStatement(DB_VERIFICA_USUARIO_LOGADO);

			ps.setString(1, nomeUsuario);
			ps.setInt(2, chave);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getBoolean("isLogado")) {
					estado = "logado";
				} else {
					estado = "deslogado";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return estado;
	}

	public boolean verificaChamadaAbertaProfessor(String usuario) {

		boolean isAberta = false;

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection
					.prepareStatement(DB_VERIFICA_CHAMADA_ABERTA_PROFESSOR);

			ps.setString(1, usuario);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (!rs.getBoolean("fim_aula")) {
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
				if (!rs.getBoolean("fim_aula")) {
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
