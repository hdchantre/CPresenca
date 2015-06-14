package Control;


import DAO.LoginDAO;
import Model.Aluno;
import Model.Professor;
import Model.Usuario;
import XML.UsuarioLogin;

public class ControleLogin {

	LoginDAO loginDAO = new LoginDAO();

	public Usuario tentarLogar(String nomeUsuario, String senha) {

		Usuario usuario = loginDAO.tentarLogar(nomeUsuario, senha);

		return usuario;
	}
	
	public Usuario tentarDeslogar(String nomeUsuario, String tipo, Integer chave) {

		Usuario usuario = loginDAO.tentarDeslogar(nomeUsuario, tipo, chave);
		
		return usuario;
	}
}
