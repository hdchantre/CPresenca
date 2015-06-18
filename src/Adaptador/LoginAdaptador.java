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

	public boolean logarValidoProfessor(String nomeUsuario, String senha) {
		boolean isLogado = false;
		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (usuario.getIsLogado()) {
			isLogado = true;
		}

		tentarDeslogar(nomeUsuario, "Professor");

		return isLogado;
	}

	public boolean logarValidoAluno(String nomeUsuario, String senha) {
		boolean isLogado = false;
		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (usuario.getIsLogado()) {
			isLogado = true;
		}

		tentarDeslogar(nomeUsuario, "Aluno");

		return isLogado;
	}

	public boolean logarUsuarioInvalidoProfessor(String nomeUsuario,
			String senha) {
		boolean isNotLogado = false;

		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (!usuario.getIsLogado()) {
			isNotLogado = true;
		} else {
			tentarDeslogar(nomeUsuario, "Professor");
		}

		return isNotLogado;
	}

	public boolean logarUsuarioInvalidoAluno(String nomeUsuario, String senha) {
		boolean isNotLogado = false;

		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (!usuario.getIsLogado()) {
			isNotLogado = true;
		} else {
			tentarDeslogar(nomeUsuario, "Aluno");
		}

		return isNotLogado;
	}

	public boolean senhaInvalidaProfessor(String nomeUsuario, String senha) {
		boolean isNotLogado = false;

		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (!usuario.getIsLogado()) {
			isNotLogado = true;
		} else {
			tentarDeslogar(nomeUsuario, "Professor");
		}

		return isNotLogado;
	}

	public boolean senhaInvalidaAluno(String nomeUsuario, String senha) {
		boolean isNotLogado = false;

		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (!usuario.getIsLogado()) {
			isNotLogado = true;
		} else {
			tentarDeslogar(nomeUsuario, "Aluno");
		}

		return isNotLogado;
	}

	public boolean senha_ID_Invalida_Professor(String nomeUsuario, String senha) {
		boolean isNotLogado = false;

		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (!usuario.getIsLogado()) {
			isNotLogado = true;
		} else {
			tentarDeslogar(nomeUsuario, "Professor");
		}

		return isNotLogado;
	}

	public boolean senha_ID_Invalida_Aluno(String nomeUsuario, String senha) {
		boolean isNotLogado = false;

		Usuario usuario = tentarLogar(nomeUsuario, senha);

		if (!usuario.getIsLogado()) {
			isNotLogado = true;
		} else {
			tentarDeslogar(nomeUsuario, "Aluno");
		}

		return isNotLogado;
	}

}
