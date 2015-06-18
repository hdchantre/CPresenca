package Adaptador;

import Control.ControleLogin;
import Model.Usuario;

public class LoginAdaptador implements LoginInterface {

	ControleLogin controleLogin = new ControleLogin();

	@Override
	public Usuario tentarLogar(String nomeUsuario, String senha) {
		return controleLogin.tentarLogar(nomeUsuario, senha);
	}

	@Override
	public Usuario tentarDeslogar(String nomeUsuario, String tipo) {
		return controleLogin.tentarDeslogar(nomeUsuario, tipo);
	}

	public boolean tentarLogar(String nomeUsuario, String senha, String tipo) {
		boolean isLogado = false;
		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (usuario.getIsLogado()) {
			isLogado = true;
		}

		if(isLogado){
			if(tipo.equals("Professor")){
				tentarDeslogar(nomeUsuario, "Professor");
			}else{
				tentarDeslogar(nomeUsuario, "Aluno");
			}
		}
		
		return isLogado;
	}

}
