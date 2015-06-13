package Control;

import java.util.ArrayList;
import java.util.List;

import DAO.AulaDAO;
import DAO.LoginDAO;
import Model.Turma;
import XML.InicializaChamada;
import XML.TurmaLogin;

public class ControleAula {
	
	AulaDAO aulaDAO = new AulaDAO();
	LoginDAO loginDAO = new LoginDAO();

	public InicializaChamada inicializaChamada(Integer idTurma) {
		return aulaDAO.inicializaChamada(idTurma);
	}
	
	
	public List<TurmaLogin> getTurmas(String nomeUsuario, String tipo) {
		List<TurmaLogin> turmasLogin = new ArrayList<TurmaLogin>();
		TurmaLogin turmaLogin;

		if (tipo.equals("Aluno")) {
			List<Turma> turmas = loginDAO.getTurmasAluno(nomeUsuario);

			for (Turma turma : turmas) {
				turmaLogin = new TurmaLogin();
				turmaLogin.setIdTurma(turma.getId());
				turmaLogin.setNomeDisciplina(turma.getDisciplina().getNome());
				turmaLogin.setChamadaAberta(turma.getChamadaAberta());
				turmasLogin.add(turmaLogin);
			}
		} else if (tipo.equals("Professor")) {
			List<Turma> turmas = loginDAO.getTurmasProfessor(nomeUsuario);

			for (Turma turma : turmas) {
				turmaLogin = new TurmaLogin();
				turmaLogin.setIdTurma(turma.getId());
				turmaLogin.setNomeDisciplina(turma.getDisciplina().getNome());
				turmaLogin.setChamadaAberta(turma.getChamadaAberta());
				turmasLogin.add(turmaLogin);
			}
		}

		return turmasLogin;
	}


}
