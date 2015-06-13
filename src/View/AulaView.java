package View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Control.ControleAula;
import XML.InicializaChamada;

@Path("/aula")
public class AulaView {

	ControleAula controleAula = new ControleAula();

	@GET
	@Path("/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public InicializaChamada returnVersion(@PathParam("id") Integer idTurma) {
		return controleAula.inicializaChamada(idTurma);
	}

}
