package Adaptador;

import java.util.List;

import Control.PresencaControle;
import Model.Presenca;

public class PresencaAdaptador implements PresencaInterface {

	PresencaControle presencaControle = new PresencaControle();

	@Override
	public List<Presenca> verificarPresencaTurma(String nomeUsuario,
			Integer idTurma) {
		return presencaControle.verificarPresencaTurma(nomeUsuario, idTurma);
	}

	public boolean verificarPresencas(String nomeAluno, int idTurma) {
		if (verificarPresencaTurma(nomeAluno, idTurma).isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean sairVerficarPresencas(String nomeAluno, int idTurma) {
		return true;
	}
}
