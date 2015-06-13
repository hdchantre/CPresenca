package View;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Control.ControleLogin;
import Control.ControleAula;
import XML.TurmaLogin;
import XML.UsuarioLogin;

@Path("/login")
public class LoginView {

	ControleLogin controleLogin = new ControleLogin();
	ControleAula controleAula = new ControleAula(); 

	@GET
	@Path("/usuario/{usuario}/senha/{senha}")
	@Produces(MediaType.APPLICATION_XML)
	public UsuarioLogin returnVersion(@PathParam("usuario") String nameUsuario,
			@PathParam("senha") String senha) {
		return controleLogin.tentarLogar(nameUsuario, senha);
	}

	@GET
	@Path("turma/usuario/{usuario}/tipo/{tipo}")
	@Produces(MediaType.APPLICATION_XML)
	public List<TurmaLogin> getTurma(@PathParam("usuario") String nameUsuario, @PathParam("tipo") String tipo) {
		return controleAula.getTurmas(nameUsuario, tipo);
	}

}
