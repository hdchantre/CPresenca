package View;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Control.ControleAula;
import XML.InicializaChamada;
import XML.TurmaLogin;

@Path("/aula")
public class AulaView {

	ControleAula controleAula = new ControleAula();

	@GET
	@Path("/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public InicializaChamada returnVersion(@PathParam("id") Integer idTurma) {
		return controleAula.inicializaChamada(idTurma);
	}
	
	@GET
	@Path("turma/usuario/{usuario}/tipo/{tipo}")
	@Produces(MediaType.APPLICATION_XML)
	public List<TurmaLogin> getTurma(@PathParam("usuario") String nameUsuario, @PathParam("tipo") String tipo) {
		return controleAula.getTurmas(nameUsuario, tipo);
	}

}
