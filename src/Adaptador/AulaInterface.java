package Adaptador;

import java.util.List;

import Model.Chamada;
import Model.Turma;

public interface AulaInterface {
	
	//H4
	public abstract Chamada inicializaChamada(String nomeUsuario, Integer idTurma, float posix, float posiy, Integer porpre, Integer tempoTicket);
	
	//H5
	public abstract Chamada fecharAula(Integer idTurma);
	
	//H??? - Não tem em nenhuma historia
	public abstract List<Turma> getTurmas(String nomeUsuario, String tipo);
	
	//H6
	public abstract Chamada checkAluno(String nomeUsuario, Integer idTurma);
	
	//H
	public abstract Chamada checkOutAluno(String nomeUsuario);
	
	//H8
	public abstract Chamada ticket(String nomeUsuario, float posiX, float posiY);

}
