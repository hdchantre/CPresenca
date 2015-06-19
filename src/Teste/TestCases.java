package Teste;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Adaptador.LoginAdaptador;
import Control.ControleAula;
import Control.ControleLogin; //import Control.ControleTurma;
import StateMachines.FSMServidor;
import StateMachines.StateServer;
import Model.Chamada;
import Model.Usuario;

/**
 * @author Hernani Chantre
 * 
 */
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases {

	private LoginAdaptador loginAdaptador;
	private FSMServidor fsmservidor;
	private ControleAula controleAula;

	@Before
	public void setUp() throws Exception {
		loginAdaptador = new LoginAdaptador();

		fsmservidor = new FSMServidor();

		controleAula = new ControleAula();

	}

	@Test
	public void logarValidoProfessor() {
		fsmservidor.getState();

		boolean isLogado = loginAdaptador.tentarLogar("Eliane",
				"12345", "Professor");

		if (isLogado) {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

		} else
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));
	}

	@Test
	public void logarValidoAluno() {
		fsmservidor.getState();

		boolean isLogado = loginAdaptador.tentarLogar("Joao",
				"12345", "Aluno");

		if (isLogado) {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

		} else
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));
	}

	@Test
	public void logarUsuarioInvalidoProfessor() {

		fsmservidor.loginInvalido();
		boolean isLogado = loginAdaptador.tentarLogar("Elianee",
				"12345", "Professor");

		if (!isLogado) {
			fsmservidor.loginInvalido();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));

		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}

	}

	@Test
	public void logarUsuarioInvalidoAluno() {

		fsmservidor.loginInvalido();
		boolean isLogado = loginAdaptador.tentarLogar("Joaoa",
				"12345", "Aluno");

		if (!isLogado) {
			fsmservidor.loginInvalido();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));

		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}

	}

	@Test
	public void senhaInvalidaProfessor() {

		fsmservidor.loginInvalido();
		boolean isLogado = loginAdaptador.tentarLogar("Eliane",
				"123451", "Professor");

		if (!isLogado) {
			fsmservidor.loginInvalido();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));

		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void senhaInvalidaAluno() {

		fsmservidor.loginInvalido();
		boolean isLogado = loginAdaptador.tentarLogar("Joao",
				"123451", "Aluno");

		if (!isLogado) {
			fsmservidor.loginInvalido();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));

		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}

	@Test
	public void senha_ID_Invalida_Professor() {

		fsmservidor.loginInvalido();
		boolean isLogado = loginAdaptador.tentarLogar("Elianee",
				"123451", "Professor");

		if (!isLogado) {
			fsmservidor.loginInvalido();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));

		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void senha_ID_Invalida_Aluno() {

		fsmservidor.loginInvalido();
		boolean isLogado = loginAdaptador.tentarLogar("Joaao",
				"123451", "Aluno");

		if (!isLogado) {
			fsmservidor.loginInvalido();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));

		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarValidoProfessor() {


		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarProfessor("Eliane",
				"Professor", true);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarJaDeslogadoProfessor() {


		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarProfessor("Eliane",
				"Professor", false);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarUsarioInvalidoProfessor() {


		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarProfessor("Elianee",
				"Professor", true);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarJaDeslogadoUsuarioInvalidoProfessor() {


		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarProfessor("Elianee",
				"Professor", false);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarValidoAluno() {

		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joao",
				"Aluno", true);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarJaDeslogadoAluno() {


		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joao",
				"Aluno", false);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarUsarioInvalidoAluno() {


		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joaoa",
				"Aluno", true);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	
	@Test
	public void deslogarJaDeslogadoUsuarioInvalidoAluno() {


		fsmservidor.setState("logado");
		boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joaoa",
				"Aluno", false);

		if (isDeslogado) {
			fsmservidor.efetuarLogout();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		} else {
			fsmservidor.loginServidor();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.inativo.toString()));
		}
	}
	

	@Test
	public void abrirSessaoDaAula() {

		fsmservidor.setState("logado");

		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);

		if (!chamadaTeste.getChamadaAberta()) {
			fsmservidor.entrarEmAula();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.emAula.toString()));

		} else
			assertEquals(true,
					(fsmservidor.getState() == StateServer.emAula.toString()));

	}

	@Test
	public void abrirSessaoJaAberta() {

		fsmservidor.setState("emAula");

		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);

		if (chamadaTeste == null) {

			assertEquals(true,
					(fsmservidor.getState() == StateServer.emAula.toString()));

		} else
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

	}

	@Test
	public void fecharSecaoAula() {

		FSMServidor fsmservidor = new FSMServidor();
		fsmservidor.setState("emAula");
		Chamada chamadaTeste = controleAula.fecharAula(1);

		if (!chamadaTeste.getChamadaAberta()) {
			fsmservidor.sairdaAula();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

		} else
			assertEquals(true,
					(fsmservidor.getState() == StateServer.computandoAula
							.toString()));

	}

}
