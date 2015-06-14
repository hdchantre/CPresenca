package View;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Control.ControleAula;
import Model.Chamada;
import Model.Turma;
import XML.InicializaChamada;
import XML.TurmaLogin;

@Path("/aula")
public class AulaView {

	ControleAula controleAula = new ControleAula();

	@GET
	@Path("/usuario/{usuario}/turmaId/{id}/chave/{chave}")
	@Produces(MediaType.APPLICATION_XML)
	public InicializaChamada inicializaChamada(
			@PathParam("usuario") String nameUsuario,
			@PathParam("id") Integer idTurma, @PathParam("chave") Integer chave) {

		InicializaChamada iChamada = new InicializaChamada();

		Chamada chamada = controleAula.inicializaChamada(nameUsuario, idTurma,
				chave);

		if (chamada == null) {
			iChamada.setInicializada(false);
			iChamada.setCausaDoProblema("Chamada ja aberta");
		} else if (chamada.getChamadaAberta()) {
			iChamada.setInicializada(true);
		} else {
			iChamada.setInicializada(false);
			iChamada.setCausaDoProblema("Chave errada");
		}

		return iChamada;
	}
	
	@GET
	@Path("/usuario/{usuario}/tipo/{tipo}/chave/{chave}")
	@Produces(MediaType.APPLICATION_XML)
	public List<TurmaLogin> getTurma(@PathParam("usuario") String nameUsuario,
			@PathParam("tipo") String tipo, @PathParam("chave") Integer chave) {

		List<TurmaLogin> turmasLogin = new ArrayList<TurmaLogin>();
		TurmaLogin turmaLogin;

		List<Turma> turmas = controleAula.getTurmas(nameUsuario, tipo, chave);

		if (turmas == null) {
			turmaLogin = new TurmaLogin();
			turmaLogin.setErro("Chave errada");
			turmasLogin.add(turmaLogin);
			return turmasLogin;
		}

		for (Turma turma : turmas) {
			turmaLogin = new TurmaLogin();
			turmaLogin.setIdTurma(turma.getId());
			turmaLogin.setNomeDisciplina(turma.getDisciplina().getNome());
			turmaLogin.setChamadaAberta(turma.getChamadaAberta());
			turmasLogin.add(turmaLogin);
		}

		return turmasLogin;
	}

}
