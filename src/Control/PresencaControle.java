package Control;

import java.util.List;

import Adaptador.PresencaInterface;
import DAO.PresencaDAO;
import Model.Presenca;

public class PresencaControle implements PresencaInterface {

	PresencaDAO presencaDAO = new PresencaDAO();

	@Override
	public List<Presenca> verificarPresencaTurma(String nomeUsuario,
			Integer idTurma) {
		return presencaDAO.verificarPresencaTurma(nomeUsuario, idTurma);
	}

}
