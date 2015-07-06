package View;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Control.PresencaControle;
import Model.Aluno;
import Model.Presenca;
import XML.AlunoTurma;
import XML.ChamadaInfo;
import XML.PresencaTurma;

@Path("/presenca")
public class PresencaView {

	PresencaControle presencaControle = new PresencaControle();

	@GET
	@Path("/usuario/{usuario}/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public List<PresencaTurma> inicializaChamada(
			@PathParam("usuario") String nameUsuario,
			@PathParam("id") Integer idTurma) {

		DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
		String output;

		List<PresencaTurma> lista = new ArrayList<PresencaTurma>();
		PresencaTurma presencaTurma;

		List<Presenca> presencaLista = presencaControle.verificarPresencaTurma(
				nameUsuario, idTurma);

		for (Presenca presenca : presencaLista) {
			presencaTurma = new PresencaTurma();
			output = outputFormatter.format(presenca.getDiaChamada());
			presencaTurma.setDiaChamada(output);
			presencaTurma.setIsPresente(presenca.getIsPresente());
			lista.add(presencaTurma);
		}

		return lista;
	}

	@GET
	@Path("/turmaId/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public List<AlunoTurma> getListaAlunoTurma(@PathParam("id") Integer idTurma) {
		List<AlunoTurma> lista = new ArrayList<AlunoTurma>();
		AlunoTurma alunoTurma;

		List<Aluno> aluno = presencaControle.getListaAlunoPorTurma(idTurma);

		for (Aluno a : aluno) {
			alunoTurma = new AlunoTurma();
			alunoTurma.setId(a.getID());
			alunoTurma.setNome(a.getNome());
			alunoTurma.setUsuario(a.getLogin());
			lista.add(alunoTurma);
		}

		return lista;
	}

}
