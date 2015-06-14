package View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Control.ControleLogin;
import Model.Aluno;
import Model.Professor;
import Model.Usuario;
import XML.UsuarioLogin;

@Path("/login")
public class LoginView {

	ControleLogin controleLogin = new ControleLogin();

	@GET
	@Path("/usuario/{usuario}/senha/{senha}")
	@Produces(MediaType.APPLICATION_XML)
	public UsuarioLogin logar(@PathParam("usuario") String nameUsuario,
			@PathParam("senha") String senha) {
		UsuarioLogin usuarioLogin = new UsuarioLogin();
		usuarioLogin.setSucess(false);

		Usuario usuario = new Usuario();

		usuario = controleLogin.tentarLogar(nameUsuario, senha);

		if (usuario instanceof Aluno) {
			usuarioLogin.setSucess(true);
			usuarioLogin.setTipo("Aluno");
			usuarioLogin.setChave(usuario.getChave());
		} else if (usuario instanceof Professor) {
			usuarioLogin.setSucess(true);
			usuarioLogin.setTipo("Professor");
			usuarioLogin.setChave(usuario.getChave());
		}

		return usuarioLogin;
	}
	
	@GET
	@Path("/usuario/{usuario}/tipo/{tipo}/chave/{chave}")
	@Produces(MediaType.APPLICATION_XML)
	public UsuarioLogin deslogar(@PathParam("usuario") String nameUsuario,
			@PathParam("tipo") String tipo, @PathParam("chave") Integer chave) {
		
		UsuarioLogin usuarioLogin = new UsuarioLogin();
		usuarioLogin.setSucess(false);

		Usuario usuario = new Usuario();

		usuario = controleLogin.tentarDeslogar(nameUsuario, tipo, chave);

		if(usuario==null){
			usuarioLogin.setTipo("Chave errada");
		}else if(!usuario.getIsLogado()){
			usuarioLogin.setSucess(true);
		}else if(usuario.getInAula()){
			usuarioLogin.setTipo("Em aula");
		}else{
			usuarioLogin.setTipo("Erro DB");
		}
		
		return usuarioLogin;
	}
}
