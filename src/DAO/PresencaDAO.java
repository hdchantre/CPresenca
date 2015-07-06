package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Aluno;
import Model.Presenca;

public class PresencaDAO {

	MainDAO mainDAO = new MainDAO();
	CommonDAO commonDAO = new CommonDAO();

	Connection connection;

	private static final String DB_SELECT_ALL_CHAMADAS = "select * from chamada where turma = ?";
	private static final String DB_VERIFICA_ALUNO_PRESENTE = "select is_presente from chamada_aluno where aluno_id = (select id from usuario where usuario = ?)  and chamada_id = ?";
	private static final String DB_GET_LISTA_ALUNO_TURMA = "select nome, id, usuario from usuario where id in (select aluno from turma_aluno where turma=?)";

	public List<Presenca> verificarPresencaTurma(String usuario, Integer turmaID) {
		List<Presenca> listaPresenca = new ArrayList<Presenca>();
		Presenca presenca;

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_SELECT_ALL_CHAMADAS);

			ps.setInt(1, turmaID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				presenca = new Presenca();
				presenca.setDiaChamada(rs.getDate("data_chamada"));
				presenca.setIsPresente(false);

				ps = connection.prepareStatement(DB_VERIFICA_ALUNO_PRESENTE);

				ps.setString(1, usuario);
				ps.setInt(2, rs.getInt("id"));

				ResultSet rs2 = ps.executeQuery();

				if (rs2.next()) {
					presenca.setIsPresente(rs2.getBoolean("is_presente"));
				}

				listaPresenca.add(presenca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return listaPresenca;
	}

	public List<Aluno> getListaAlunoPorTurma(Integer turmaID) {
		List<Aluno> listaAluno = new ArrayList<Aluno>();
		Aluno aluno;

		connection = mainDAO.conectarDB();

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(DB_GET_LISTA_ALUNO_TURMA);

			ps.setInt(1, turmaID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				aluno = new Aluno();
				aluno.setNome(rs.getString("nome"));
				aluno.setID(rs.getInt("id"));
				aluno.setLogin(rs.getString("usuario"));
				listaAluno.add(aluno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mainDAO.fecharConexaoDB();

		return listaAluno;
	}

}
