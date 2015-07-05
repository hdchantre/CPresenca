package Adaptador;

import java.util.List;

import Control.PresencaControle;
import Model.Presenca;

public class PresencaAdaptador implements PresencaInterface {

	PresencaControle presencaControle = new PresencaControle();

	//auxiliar em H23
	@Override
	public List<Presenca> verificarPresencaTurma(String nomeUsuario,
			Integer idTurma) {
		return presencaControle.verificarPresencaTurma(nomeUsuario, idTurma);
	}

	//H15 e H17
	public boolean verificarPresencas(String nomeAluno, int idTurma) {
		if (verificarPresencaTurma(nomeAluno, idTurma).isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	//H15 e H17
	public boolean sairVerficarPresencas(String nomeAluno, int idTurma) {
		return true;
	}
	
	//H15
	public boolean consultarLista (Integer idTurma)
	{
		return true;
	}
	
	//H15
	public boolean sairconsultarLista (Integer idTurma)
	{
		return true;
	}
	
	//H9
	public boolean configurar (Integer parametro)
	{
		return true;
	}
	
	//H9
	public boolean sairconfigurar ()
	{
		return true;
	}

	
}
