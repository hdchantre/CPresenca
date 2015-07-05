package View;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Control.ControleAula;
import Model.Aluno;
import Model.Chamada;
import Model.Turma;
import XML.FinalizaChamada;
import XML.InicializaChamada;
import XML.Ticket;
import XML.TurmaLogin;

@Path("/aula")
public class AulaView {

	ControleAula controleAula = new ControleAula();

	//H4
	@GET
	@Path("/usuario/{usuario}/turmaId/{id}/posix/{x}/posiy/{y}/porpre/{porpre}/dura/{dura}")
	@Produces(MediaType.APPLICATION_XML)
	public InicializaChamada inicializaChamada(
			@PathParam("usuario") String nameUsuario,
			@PathParam("id") Integer idTurma, @PathParam("x") float posiX,
			@PathParam("y") float posiY, @PathParam("porpre") Integer porpre,
			@PathParam("dura") Integer dura) {

		InicializaChamada iChamada = new InicializaChamada();

		Chamada chamada = controleAula.inicializaChamada(nameUsuario, idTurma,
				posiX, posiY, porpre, dura);

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

	//Sem historia
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

	//H5
	@GET
	@Path("/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public List<FinalizaChamada> fecharChamada(@PathParam("id") Integer idTurma) {

		List<FinalizaChamada> fChamadaList = new ArrayList<FinalizaChamada>();

		FinalizaChamada fChamada;

		Chamada chamada = controleAula.fecharAula(idTurma);

		if (chamada.getChamadaAberta()) {
			fChamada = new FinalizaChamada();
			fChamada.setFinalizada(false);
			fChamada.setCausaDoProblema("Chamada continua aberta");
			fChamadaList.add(fChamada);
		} else {
			for (Aluno aluno : chamada.getAlunos().keySet()) {
				fChamada = new FinalizaChamada();
				fChamada.setFinalizada(true);
				fChamada.setAluno(aluno.getNome());
				fChamada.setPresenca(chamada.getAlunos().get(aluno));
				fChamadaList.add(fChamada);
			}
		}

		return fChamadaList;
	}

	//H6
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
			iChamada.setChamadaID(chamada.getId());
		} else {
			iChamada.setInicializada(false);
			iChamada.setCausaDoProblema("Chamada fechada");
		}

		return iChamada;
	}

	//Aux-H6
	@GET
	@Path("/aluno/{usuario}/chamada/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public InicializaChamada checkOutAluno(@PathParam("id") Integer chamadaId,
			@PathParam("usuario") String nameUsuario) {

		InicializaChamada iChamada = new InicializaChamada();

		Chamada chamada = controleAula.checkOutAluno(nameUsuario);

		if (chamada.getChamadaAberta()) {
			iChamada.setInicializada(true);
			iChamada.setCausaDoProblema("Continua em Aula");
		} else {
			iChamada.setInicializada(false);
			if (controleAula.verificarPresencaAluno(chamadaId, nameUsuario)) {
				iChamada.setIsPresente(true);
			} else {
				iChamada.setIsPresente(false);
			}
		}

		return iChamada;
	}

	//H8
	@GET
	@Path("aluno/{usuario}/posix/{x}/posiy/{y}")
	@Produces(MediaType.APPLICATION_XML)
	public Ticket ticket(@PathParam("usuario") String nameUsuario,
			@PathParam("x") float posiX, @PathParam("y") float posiY) {

		Ticket ticket = new Ticket();

		Chamada chamada = controleAula.ticket(nameUsuario, posiX, posiY);

		if (chamada.getChamadaAberta()) {
			ticket.setTicketRecebido(true);
			ticket.setFimAula(false);
		} else {
			ticket.setTicketRecebido(false);
			ticket.setFimAula(true);
		}

		return ticket;
	}

}
