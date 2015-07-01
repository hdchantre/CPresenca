package Teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Adaptador.AulaAdaptador;
import Adaptador.LoginAdaptador;

import StateMachines.FSMServidor;
import StateMachines.StateServer;


/**
 * @author Hernani Chantre
 * 
 */
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCasesProfessor {

	private LoginAdaptador loginAdaptador;
	private AulaAdaptador auladaptador;
	private FSMServidor fsmservidor;
	

	@Before
	public void setUp() throws Exception {
		loginAdaptador = new LoginAdaptador();

		fsmservidor = new FSMServidor();

		auladaptador = new AulaAdaptador();

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
					(fsmservidor.getState() == StateServer.logado.toString()));
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
					(fsmservidor.getState() == StateServer.logado.toString()));
		}
	}
	
	

	@Test
	public void abrirSessaoDaAula() {

		fsmservidor.setState("logado");

		boolean isInicializada = auladaptador.inicializaChamada("Eliane", 1, false);

		if (isInicializada) {
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

		boolean isInicializada = auladaptador.inicializaChamada("Eliane", 1, true);

		if (!isInicializada) {
			assertEquals(true,
					(fsmservidor.getState() == StateServer.emAula.toString()));
		} 

	}
	
	@Test
	public void abrirSessaoTurmaIdErrada() {

		fsmservidor.setState("logado");

		boolean isInicializada = auladaptador.inicializaChamada("Eliane", 1, false);

		if (!isInicializada) {
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

		}
	}
	
	@Test
	public void fecharSessaoDaAula() {

		fsmservidor.setState("emAula");

		boolean isFechada = auladaptador.fecharAula(1, true);

		if (isFechada) {
			fsmservidor.sairdaAula();
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

		} else
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

	}
	
	@Test
	public void fecharSessaoDaAulaJaFechada() {

		fsmservidor.setState("logado");

		boolean isFechada = auladaptador.fecharAula(1, false);

		if (!isFechada) {
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

		} else
			assertEquals(true,
					(fsmservidor.getState() == StateServer.logado.toString()));

	}
}
