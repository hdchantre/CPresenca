package Adaptador;

import java.util.List;

import Model.Presenca;

public interface PresencaInterface {

	public abstract List<Presenca> verificarPresencaTurma(String nomeUsuario,
			Integer idTurma);

}
