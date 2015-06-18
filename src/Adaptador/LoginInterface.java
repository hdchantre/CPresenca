package Adaptador;

import Model.Usuario;

public interface LoginInterface {

	public abstract Usuario tentarLogar(String nomeUsuario, String senha);

	public abstract Usuario tentarDeslogar(String nomeUsuario, String tipo);

}
