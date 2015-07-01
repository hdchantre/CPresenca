package Adaptador;

import java.util.List;

import Model.Chamada;
import Model.Turma;

public interface AulaInterface {
	
	public abstract Chamada inicializaChamada(String nomeUsuario, Integer idTurma);
	
	public abstract Chamada fecharAula(Integer idTurma);
	
	public abstract List<Turma> getTurmas(String nomeUsuario, String tipo);
	
	public abstract Chamada checkAluno(String nomeUsuario, Integer idTurma);
	
	public abstract Chamada checkOutAluno(String nomeUsuario);
	
	public abstract boolean ticket(String nomeUsuario, float posiX, float posiY);

}
