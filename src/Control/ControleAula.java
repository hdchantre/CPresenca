package Control;

import java.util.ArrayList;
import java.util.List;

import Adaptador.AulaInterface;
import DAO.AulaDAO;
import DAO.LoginDAO;
import Model.Chamada;
import Model.Turma;

public class ControleAula implements AulaInterface {

	AulaDAO aulaDAO = new AulaDAO();
	LoginDAO loginDAO = new LoginDAO();

	public Chamada inicializaChamada(String nomeUsuario, Integer idTurma) {
		return aulaDAO.inicializaChamada(nomeUsuario, idTurma);
	}

	public Chamada fecharAula(Integer idTurma) {
		Chamada chamada = aulaDAO.contabiliza(idTurma);
		chamada.setChamadaAberta(aulaDAO.finalizaChamada(idTurma).getChamadaAberta());
		return chamada;
	}

	public List<Turma> getTurmas(String nomeUsuario, String tipo) {
		List<Turma> turmas = new ArrayList<Turma>();

		if (tipo.equals("Aluno")) {
			turmas = aulaDAO.getTurmasAluno(nomeUsuario);
		} else if (tipo.equals("Professor")) {
			turmas = aulaDAO.getTurmasProfessor(nomeUsuario);
		}

		return turmas;
	}

	@Override
	public Chamada checkAluno(String nomeUsuario, Integer idTurma) {
		return aulaDAO.checkAluno(nomeUsuario, idTurma);
	}

	@Override
	public Chamada checkOutAluno(String nomeUsuario) {
		return aulaDAO.checkOutAluno(nomeUsuario);
	}

	@Override
	public Chamada ticket(String nomeUsuario, float posiX, float posiY) {
		return aulaDAO.ticketAluno(nomeUsuario, posiX, posiY);
	}

}
