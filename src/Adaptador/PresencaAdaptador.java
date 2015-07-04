package Adaptador;

import java.util.List;

import Model.Presenca;

public class PresencaAdaptador implements PresencaInterface{
	
	@Override
	public List<Presenca> verificarPresencaTurma(String nomeUsuario,
			Integer idTurma) {
		return null;
	}

	public boolean verificarPresencas (String nomeAluno, int idTurma)
	{
		return true;
	}
	
	public boolean sairVerficarPresencas (String nomeAluno, int idTurma)
	{
		return true;
	}
}
