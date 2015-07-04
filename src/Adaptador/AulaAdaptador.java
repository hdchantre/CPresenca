package Adaptador;

import java.util.List;

import Control.ControleAula;
import Model.Chamada;
import Model.Turma;

public class AulaAdaptador implements AulaInterface {

	ControleAula controleAula = new ControleAula();
	LoginAdaptador loginAdaptador = new LoginAdaptador();

	@Override
	public Chamada inicializaChamada(String nomeUsuario, Integer idTurma) {
		return controleAula.inicializaChamada(nomeUsuario, idTurma);
	}

	@Override
	public Chamada fecharAula(Integer idTurma) {
		return controleAula.fecharAula(idTurma);
	}

	@Override
	public List<Turma> getTurmas(String nomeUsuario, String tipo) {
		return controleAula.getTurmas(nomeUsuario, tipo);
	}

	@Override
	public Chamada checkAluno(String nomeUsuario, Integer idTurma) {
		return controleAula.checkAluno(nomeUsuario, idTurma);
	}

	@Override
	public Chamada checkOutAluno(String nomeUsuario) {
		return controleAula.checkOutAluno(nomeUsuario);
	}
	
	@Override
	public Chamada ticket(String nomeUsuario, float posiX, float posiY) {
		return controleAula.ticket(nomeUsuario, posiX, posiY);
	}

	public boolean inicializaChamada(String nomeUsuario, Integer idTurma,
			boolean jaAberta) {
		boolean isInizializada = false;

		loginAdaptador.tentarLogar("Eliane", "12345");

		if (jaAberta) {
			inicializaChamada("Eliane", 1);
		}

		Chamada chamada = inicializaChamada(nomeUsuario, idTurma);

		if (chamada != null && chamada.getChamadaAberta()) {
			isInizializada = true;
			if (jaAberta) {
				fecharAula(1);
			} else {
				fecharAula(idTurma);
			}
		}

		loginAdaptador.tentarDeslogar("Eliane", "Professor");

		return isInizializada;
	}

	public boolean fecharAula(Integer idTurma, boolean isAberta) {
		boolean isFechada = false;

		loginAdaptador.tentarLogar("Eliane", "12345");

		if (isAberta) {
			inicializaChamada("Eliane", idTurma);
		}

		Chamada chamada = fecharAula(idTurma);

		if (!chamada.getChamadaAberta()) {
			isFechada = true;
			fecharAula(idTurma);
		}

		loginAdaptador.tentarDeslogar("Eliane", "Professor");

		return isFechada;
	}

	public boolean entrarEmAula(String nomeUsuario, Integer idTurma,
			boolean isAberta) {
		boolean entrou = false;

		loginAdaptador.tentarLogar("Joao", "12345");

		if (isAberta) {
			loginAdaptador.tentarLogar("Eliane", "12345");
			inicializaChamada("Eliane", idTurma);
		}

		Chamada chamada = checkAluno(nomeUsuario, idTurma);

		if (chamada.getChamadaAberta()) {
			entrou = true;
		}

		if (isAberta) {
			fecharAula(idTurma);
			loginAdaptador.tentarDeslogar("Eliane", "Professor");
			checkOutAluno(nomeUsuario);
		}

		loginAdaptador.tentarDeslogar("Joao", "Aluno");

		return entrou;
	}

	public boolean sairDaAula(String nomeUsuario) {
		boolean saiu = false;

		loginAdaptador.tentarLogar("Eliane", "12345");
		inicializaChamada("Eliane", 1);
		
		loginAdaptador.tentarLogar("Joao", "12345");
		checkAluno(nomeUsuario, 1);

		Chamada chamada = checkOutAluno(nomeUsuario);

		if (!chamada.getChamadaAberta()) {
			saiu = true;
		}

		fecharAula(1);
		loginAdaptador.tentarDeslogar("Eliane", "Professor");

		loginAdaptador.tentarDeslogar("Joao", "Aluno");
		
		return saiu;
	}
	
	public boolean enviarTicket(String nomeUsuario, float posix, float posiy)
	{
		Boolean check = false;
		
		loginAdaptador.tentarLogar("Eliane", "12345");
		
		inicializaChamada("Eliane", 1);
		
		loginAdaptador.tentarLogar(nomeUsuario, "12345");
		
		checkAluno(nomeUsuario, 1);
		
		check = ticket(nomeUsuario, posix, posiy).getChamadaAberta();
		
		fecharAula(1);
		loginAdaptador.tentarDeslogar("Eliane", "Professor");

		loginAdaptador.tentarDeslogar(nomeUsuario, "Aluno");
		
		return check;
	}

	

}
