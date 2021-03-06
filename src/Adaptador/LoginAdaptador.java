package Adaptador;

import Control.ControleLogin;
import Model.Usuario;

public class LoginAdaptador implements LoginInterface {

	ControleLogin controleLogin = new ControleLogin();

	//H11 e H13
	@Override
	public Usuario tentarLogar(String nomeUsuario, String senha) {
		return controleLogin.tentarLogar(nomeUsuario, senha);
	}
	
	//H12 e H14
	@Override
	public Usuario tentarDeslogar(String nomeUsuario, String tipo) {
		return controleLogin.tentarDeslogar(nomeUsuario, tipo);
	}

	//H11 e H13
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
	
	//H12 e H14
	public boolean tentarDeslogarProfessor(String nomeUsuario, String tipo, boolean isLogado){
		boolean deslogado = false;
		
		if(isLogado){
			tentarLogar("Eliane", "12345");
		}
		
		Usuario usuario = tentarDeslogar(nomeUsuario, tipo);
		
		if(usuario==null){
			if(isLogado){
				tentarDeslogar("Eliane", "Professor");
			}
			return deslogado;
		}
		
		if(!usuario.getIsLogado()){
			deslogado = true;
		}else{
			tentarDeslogar("Eliane", "Professor");
		}
		
		return deslogado;
	}
	
	//H11 e H13
	public boolean tentarDeslogarAluno(String nomeUsuario, String tipo, boolean isLogado){
		boolean deslogado = false;
		
		if(isLogado){
			tentarLogar("Joao", "12345");
		}
		
		Usuario usuario = tentarDeslogar(nomeUsuario, tipo);
		
		if(usuario==null){
			if(isLogado){
				tentarDeslogar("Joao", "Aluno");
			}
			return deslogado;
		}
		
		if(!usuario.getIsLogado()){
			deslogado = true;
		}else{
			tentarDeslogar("Joao", "Aluno");
		}
		
		return deslogado;
	}

}
