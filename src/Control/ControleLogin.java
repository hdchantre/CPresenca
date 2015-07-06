package Control;

import Adaptador.LoginInterface;
import DAO.LoginDAO;
import Model.Usuario;

public class ControleLogin implements LoginInterface {

	LoginDAO loginDAO = new LoginDAO();

	//H11 e H13
	public Usuario tentarLogar(String nomeUsuario, String senha) {

		Usuario usuario = loginDAO.tentarLogar(nomeUsuario, senha);

		return usuario;
	}

	//H12 e H14
	public Usuario tentarDeslogar(String nomeUsuario, String tipo) {

		Usuario usuario = loginDAO.tentarDeslogar(nomeUsuario, tipo);

		return usuario;
	}
}
