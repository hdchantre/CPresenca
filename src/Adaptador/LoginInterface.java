package Adaptador;

import Model.Usuario;

public interface LoginInterface {

	//H11 e H13
	public abstract Usuario tentarLogar(String nomeUsuario, String senha);

	//H12 e H14
	public abstract Usuario tentarDeslogar(String nomeUsuario, String tipo);

}
