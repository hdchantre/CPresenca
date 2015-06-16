package Control;

import java.util.ArrayList;
import java.util.List;

import DAO.AulaDAO;
import DAO.LoginDAO;
import Model.Chamada;
import Model.Turma;
import XML.InicializaChamada;
import XML.TurmaLogin;

public class ControleAula {

	AulaDAO aulaDAO = new AulaDAO();
	LoginDAO loginDAO = new LoginDAO();

	public Chamada inicializaChamada(String nomeUsuario, Integer idTurma) {
		return aulaDAO.inicializaChamada(nomeUsuario, idTurma);
	}
	
	public Chamada fecharAula(Integer idTurma) {
		return aulaDAO.finalizaChamada(idTurma);
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

	/*// Esse m�todo deve computar as presen�as e chamar o fechar aula
	public boolean computarAula(Integer idTurma) {
		fecharAula();
		return true;
	}

	// Na verdade esse m�todo deve retornar o relat�rio de presen�a computada
	// nao sei como isso deve ser feito ainda
	public boolean fecharAula() {
		return true;
	}*/

}
