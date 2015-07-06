package Adaptador;

import java.util.List;

import Model.Presenca;

public interface PresencaInterface {

	//auxiliar em H23
	public abstract List<Presenca> verificarPresencaTurma(String nomeUsuario,
			Integer idTurma);
}
