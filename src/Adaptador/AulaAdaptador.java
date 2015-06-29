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

	public boolean inicializaChamada(String nomeUsuario, Integer idTurma,
			boolean jaAberta) {
		boolean isInizializada = false;
		
		loginAdaptador.tentarLogar("Eliane", "12345");
		
		if(jaAberta){
			inicializaChamada("Eliane", 1);
		}
		
		Chamada chamada = inicializaChamada(nomeUsuario, idTurma);
		
		if(chamada!=null && chamada.getChamadaAberta()){
			isInizializada = true;
			if(jaAberta){
				fecharAula(1);
			}else{
				fecharAula(idTurma);
			}
		}
		
		loginAdaptador.tentarDeslogar("Eliane", "Professor");

		return isInizializada;
	}
	
	public boolean fecharAula(Integer idTurma, boolean isAberta) {
		boolean isFechada = false;
		
		loginAdaptador.tentarLogar("Eliane", "12345");
		
		if(isAberta){
			inicializaChamada("Eliane", idTurma);
		}
		
		Chamada chamada = fecharAula(idTurma);
		
		if(!chamada.getChamadaAberta()){
			isFechada = true;
			fecharAula(idTurma);
		}
		
		loginAdaptador.tentarDeslogar("Eliane", "Professor");

		return isFechada;
	}
	
	
	public boolean entrarEmAula(String nomeUsuario, Integer idTurma, boolean isAberta){
		boolean entrou = false;
		
		loginAdaptador.tentarLogar("Joao", "12345");
		
		if(isAberta){
			loginAdaptador.tentarLogar("Eliane", "12345");
			inicializaChamada("Eliane", idTurma);
		}
		
		Chamada chamada = checkAluno(nomeUsuario, idTurma);
		
		if(chamada.getChamadaAberta()){
			entrou = true;
		}
		
		if(isAberta){
			fecharAula(idTurma);
			loginAdaptador.tentarDeslogar("Eliane", "Professor");
		}
		
		loginAdaptador.tentarDeslogar("Joao", "Aluno");
		
		return entrou;
	}
	
	public boolean sairDaAula(Integer idTurma){
		return true;
	}
}
