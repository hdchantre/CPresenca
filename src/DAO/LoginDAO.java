package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Aluno;
import Model.Professor;
import Model.Usuario;

public class LoginDAO {

	MainDAO mainDAO = new MainDAO();
	CommonDAO commonDAO = new CommonDAO();

	Connection connection;

	private static final String DB_SELECT_USUARIO = "select nome, tipo, islogado from usuario where usuario=? and senha=?";
	private static final String DB_UPDATE_USUARIO_LOGADO = "update usuario set islogado = true, chave = ? where usuario=? and senha=?";
	private static final String DB_SAIR = "update usuario set islogado = false, chave = null  where usuario=? and chave=?";

	public Usuario tentarLogar(String nomeUsuario, String senha) {

		Usuario usuario = new Usuario();

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_SELECT_USUARIO);

			ps.setString(1, nomeUsuario);
			ps.setString(2, senha);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Boolean islogado = rs.getBoolean("isLogado");

				if (!islogado) {
					String tipo = rs.getString("tipo");

					if (tipo.equals("Aluno")) {
						usuario = new Aluno();
					} else {
						usuario = new Professor();
					}

					usuario.setNome(rs.getString("nome"));
					usuario.setIsLogado(true);
					usuario.setChave((int) (Math.random() * 1000000));

					ps = connection.prepareStatement(DB_UPDATE_USUARIO_LOGADO);

					ps.setInt(1, usuario.getChave());
					ps.setString(2, nomeUsuario);
					ps.setString(3, senha);

					ps.executeUpdate();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return usuario;
	}

	public Usuario tentarDeslogar(String nomeUsuario, String tipo, Integer chave) {

		Usuario usuario = new Usuario();

		usuario.setIsLogado(false);
		usuario.setInAula(false);

		String estado = commonDAO.isUsuarioLogado(nomeUsuario, chave);

		if ("logado".equals(estado)) {

			PreparedStatement ps;

			connection = mainDAO.conectarDB();

			try {

				if ("Professor".equals(tipo)) {
					if (!commonDAO.verificaChamadaAbertaProfessor(nomeUsuario)) {
						ps = connection.prepareStatement(DB_SAIR);

						ps.setString(1, nomeUsuario);
						ps.setInt(2, chave);

						if (ps.executeUpdate() <= 0) {
							usuario.setIsLogado(true);
							usuario.setInAula(false);
						}
					} else {
						usuario.setIsLogado(true);
						usuario.setInAula(true);
					}
				} else if ("Aluno".equals(tipo)) {
					if (!commonDAO.verificaChamadaAbertaAluno(nomeUsuario)) {
						ps = connection.prepareStatement(DB_SAIR);

						ps.setString(1, nomeUsuario);
						ps.setInt(2, chave);

						if (ps.executeUpdate() <= 0) {
							usuario.setIsLogado(true);
							usuario.setInAula(false);
						}
					} else {
						usuario.setIsLogado(true);
						usuario.setInAula(true);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainDAO.fecharConexaoDB();
		} else if ("Chave Errada".equals(estado)) {
			return null;
		}

		return usuario;
	}
}
