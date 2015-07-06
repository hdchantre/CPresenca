package Control;

import java.util.List;

import Adaptador.PresencaInterface;
import DAO.PresencaDAO;
import Model.Aluno;
import Model.Presenca;

public class PresencaControle implements PresencaInterface {

	PresencaDAO presencaDAO = new PresencaDAO();

	//H15  e H17 
	@Override
	public List<Presenca> verificarPresencaTurma(String nomeUsuario,
			Integer idTurma) {
		return presencaDAO.verificarPresencaTurma(nomeUsuario, idTurma);
	}

	//H15 e H17
	public List<Aluno> getListaAlunoPorTurma(Integer idTurma) {
		return presencaDAO.getListaAlunoPorTurma(idTurma);
	}

}
