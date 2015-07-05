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

	//H4
	public Chamada inicializaChamada(String nomeUsuario, Integer idTurma, float posix, float posiy, Integer porpre, Integer tempoTicket) {
		return aulaDAO.inicializaChamada(nomeUsuario, idTurma, posix, posiy, porpre, tempoTicket);
	}

	//H5
	public Chamada fecharAula(Integer idTurma) {
		Chamada chamada = aulaDAO.contabiliza(idTurma);
		aulaDAO.setPresencaAluno(chamada.getAlunos());
		chamada.setChamadaAberta(aulaDAO.finalizaChamada(idTurma).getChamadaAberta());
		return chamada;
	}

	//H - Sem historia
	public List<Turma> getTurmas(String nomeUsuario, String tipo) {
		List<Turma> turmas = new ArrayList<Turma>();

		if (tipo.equals("Aluno")) {
			turmas = aulaDAO.getTurmasAluno(nomeUsuario);
		} else if (tipo.equals("Professor")) {
			turmas = aulaDAO.getTurmasProfessor(nomeUsuario);
		}

		return turmas;
	}

	//H6
	@Override
	public Chamada checkAluno(String nomeUsuario, Integer idTurma) {
		return aulaDAO.checkAluno(nomeUsuario, idTurma);
	}

	//H - Sem historia
	@Override
	public Chamada checkOutAluno(String nomeUsuario) {
		return aulaDAO.checkOutAluno(nomeUsuario);
	}

	//H8
	@Override
	public Chamada ticket(String nomeUsuario, float posiX, float posiY) { 
		return aulaDAO.ticketAluno(nomeUsuario, posiX, posiY);
	}

	//H17
	public boolean verificarPresencaAluno(Integer chamadaid, String nomeUsuario){
		return aulaDAO.verificarPresencaAluno(chamadaid, nomeUsuario);
	}
}
