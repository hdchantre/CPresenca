package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Aluno;
import Model.Disciplina;
import Model.Professor;
import Model.Turma;
import Model.Usuario;

public class LoginDAO {

	MainDAO mainDAO = new MainDAO();
	Connection connection;

	private static final String DB_SELECT_USUARIO = "select nome, tipo from usuario where usuario=? and senha=?";
	private static final String DB_GET_TURMA_PROFESSOR = "SELECT t.id, t.disciplina, d.nome, c.fim_aula FROM turma as t, disciplina as d, chamada as c WHERE datafim > CURRENT_TIMESTAMP and professor = (select id from usuario where usuario = ?) and t.disciplina = d.id and c.turma = t.id";
	private static final String DB_GET_TURMA_ALUNO = "SELECT t.id, t.disciplina, d.nome, c.fim_aula FROM turma as t, disciplina as d, turma_aluno as ta, chamada as c WHERE t.datafim > CURRENT_TIMESTAMP and ta.aluno = (select id from usuario where usuario = ?) and t.disciplina = d.id and ta.turma = t.id and c.turma = t.id";
	private static final String DB_UPDATE_USUARIO_LOGADO = "update usuario set islogado = true, chave = ? where usuario=? and senha=?";

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
					usuario.setChave((int)(Math.random()*1000000));
					
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

	public List<Turma> getTurmasProfessor(String nomeUsuario) {

		List<Turma> turmas = new ArrayList<Turma>();
		Turma turma;
		Disciplina disciplina;

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

		return turmas;
	}
	
	public List<Turma> getTurmasAluno(String nomeUsuario) {

		List<Turma> turmas = new ArrayList<Turma>();
		Turma turma;
		Disciplina disciplina;

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

		return turmas;
	}

}
