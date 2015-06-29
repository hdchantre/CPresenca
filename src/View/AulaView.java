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
import XML.FinalizaChamada;
import XML.InicializaChamada;
import XML.TurmaLogin;

@Path("/aula")
public class AulaView {

	ControleAula controleAula = new ControleAula();

	@GET
	@Path("/usuario/{usuario}/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public InicializaChamada inicializaChamada(
			@PathParam("usuario") String nameUsuario,
			@PathParam("id") Integer idTurma) {

		InicializaChamada iChamada = new InicializaChamada();

		Chamada chamada = controleAula.inicializaChamada(nameUsuario, idTurma);

		if (chamada == null) {
			iChamada.setInicializada(false);
			iChamada.setCausaDoProblema("Chamada ja aberta");
		} else if (chamada.getChamadaAberta()) {
			iChamada.setInicializada(true);
		} else {
			iChamada.setInicializada(false);
			iChamada.setCausaDoProblema("Usuario errado");
		}

		return iChamada;
	}

	@GET
	@Path("/usuario/{usuario}/tipo/{tipo}")
	@Produces(MediaType.APPLICATION_XML)
	public List<TurmaLogin> getTurma(@PathParam("usuario") String nameUsuario,
			@PathParam("tipo") String tipo) {

		List<TurmaLogin> turmasLogin = new ArrayList<TurmaLogin>();
		TurmaLogin turmaLogin;

		List<Turma> turmas = controleAula.getTurmas(nameUsuario, tipo);

		if (turmas == null) {
			turmaLogin = new TurmaLogin();
			turmaLogin.setErro("Usuario errado");
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
	
	@GET
	@Path("/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public FinalizaChamada fecharChamada(@PathParam("id") Integer idTurma) {

		FinalizaChamada fChamada = new FinalizaChamada();

		Chamada chamada = controleAula.fecharAula(idTurma);

		if (chamada.getChamadaAberta()) {
			fChamada.setFinalizada(true);
			fChamada.setCausaDoProblema("Chamada continua aberta");
		} else {
			fChamada.setFinalizada(false);
		}

		return fChamada;
	}
	
	@GET
	@Path("/aluno/{usuario}/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public InicializaChamada checkAluno(
			@PathParam("usuario") String nameUsuario,
			@PathParam("id") Integer idTurma) {

		InicializaChamada iChamada = new InicializaChamada();

		Chamada chamada = controleAula.checkAluno(nameUsuario, idTurma);

		if (chamada.getChamadaAberta()) {
			iChamada.setInicializada(true);
		} else {
			iChamada.setInicializada(false);
			iChamada.setCausaDoProblema("Chamada fechada");
		}

		return iChamada;
	}

}
