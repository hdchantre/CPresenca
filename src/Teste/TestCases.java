package Teste;

import static org.junit.Assert.assertEquals;


import org.junit.AfterClass;
import org.junit.Before;

import org.junit.Test;


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
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases {

	private ControleLogin controleLogin;
	private FSMServidor fsmservidor;
	private ControleAula controleAula;

	@Before
	public void setUp() throws Exception {
		controleLogin = new ControleLogin();

		fsmservidor = new FSMServidor();

		controleAula = new ControleAula();
		
	}

	
	@Test
	public void logarValido() {
		fsmservidor.getState();
		Usuario usuario = controleLogin.tentarLogar("Eliane", "12345");

		if (usuario.getIsLogado()) {
			fsmservidor.loginServidor();
			assertEquals(true, (fsmservidor.getState() == StateServer.logado
					.toString()));

		} else
			assertEquals(true, (fsmservidor.getState() == StateServer.logado
					.toString()));

	}


	
	@Test
	public void logarInvalido() {

		fsmservidor.loginInvalido();
		Usuario usuario = controleLogin.tentarLogar("Joaos", "123453");

		if (usuario.getIsLogado()) {
			fsmservidor.loginInvalido();
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

		} else
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

	}

	@Test
	public void senhaInvalida() {

		fsmservidor.loginInvalido();
		Usuario usuario = controleLogin.tentarLogar("Joao", "123453");

		if (usuario.getIsLogado()) {
			fsmservidor.loginInvalido();
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

		} else
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

	}

	@Test
	public void senha_ID_Invalida() {

		fsmservidor.loginInvalido();
		Usuario usuario = controleLogin.tentarLogar("Joaos", "123453");

		if (usuario.getIsLogado()) {
			fsmservidor.loginInvalido();
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

		} else
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

	}

	
	
	@Test
	public void abrirSessaoDaAula() {
			
	
		fsmservidor.setState("logado");
		
		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);
		
		if (!chamadaTeste.getChamadaAberta())
		{
			fsmservidor.entrarEmAula();
			assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		
	}
	
	
		
	
	@Test
	public void abrirSessaoJaAberta() {
			
	
		fsmservidor.setState("emAula");
		
		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);
			
		if (chamadaTeste == null)
		{
			
			assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		} else assertEquals(true,( fsmservidor.getState()== StateServer.logado.toString()));
		
		
	}
	
	@Test
	public void fecharSecaoAula() {
		
		FSMServidor fsmservidor = new FSMServidor();
		fsmservidor.setState("emAula");
		Chamada chamadaTeste = controleAula.fecharAula(1);
			
		if (!chamadaTeste.getChamadaAberta()){
			fsmservidor.sairdaAula();
			assertEquals(true,( fsmservidor.getState() == StateServer.logado.toString()));
				
		} else assertEquals(true,( fsmservidor.getState() == StateServer.computandoAula.toString()));
					
	 }

	
	//@AfterClass
	@Test
	public void deslogarValido() {
		
		ControleLogin controleLogin = new ControleLogin();
		
		FSMServidor fsmservidor = new FSMServidor();
		

		fsmservidor.setState("logado");
		Usuario usuario = controleLogin.tentarDeslogar("Eliane", "Professor");

		if (!usuario.getIsLogado()) {
			fsmservidor.efetuarLogout();
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

		} else
			assertEquals(true, (fsmservidor.getState() == StateServer.inativo
					.toString()));

	}

		
	
	

}
