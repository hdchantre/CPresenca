package Teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Control.ControleAula;
import Control.ControleLogin;
//import Control.ControleTurma;
import StateMachines.FSMServidor;
import StateMachines.StateServer;
import XML.UsuarioLogin;

public class TestCases {
	
	private ControleLogin controleLogin;
	private UsuarioLogin usuarioLogin;
	private FSMServidor  fsmservidor;
	//private ControleTurma controleTurma;
	private ControleAula controleAula;
	
	@Before
	public void setUp() throws Exception {
		controleLogin = new ControleLogin();
		usuarioLogin = new UsuarioLogin();	
		fsmservidor = new FSMServidor();
	//	controleTurma = new ControleTurma();
		controleAula = new ControleAula();
	}

	@Test
	public void logarValido() {
		
		assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
		usuarioLogin.setSucess(true);
		usuarioLogin.setTipo("Aluno");
		fsmservidor.loginServidor();
		assertEquals(usuarioLogin.getSucess(),controleLogin.tentarLogar("Joao", "12345").getSucess());
		assertEquals(usuarioLogin.getTipo(),controleLogin.tentarLogar("Joao", "12345").getTipo());
		assertEquals(true,( fsmservidor.getState()== StateServer.logado.toString()));

		
	}
	
	
	@Test
	public void logarInvalido() {
		
		assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
		usuarioLogin.setSucess(false);
		fsmservidor.loginInvalido();
		assertEquals(usuarioLogin.getSucess(),controleLogin.tentarLogar("Daniela", "123456").getSucess());
	    assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));

		
	}
	
	
	
	@Test
	public void senhaInvalida() {
		
		assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
		usuarioLogin.setSucess(false);
		fsmservidor.loginInvalido();
		assertEquals(usuarioLogin.getSucess(),controleLogin.tentarLogar("Joao", "123456").getSucess());
	    assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));

		
	}
	
	@Test
	public void senha_ID_Invalida() {
		
		assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
		usuarioLogin.setSucess(false);
		fsmservidor.loginInvalido();
		assertEquals(usuarioLogin.getSucess(),controleLogin.tentarLogar("Daniela", "12345").getSucess());
	    assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));

		
	}
	
//	@Test
//	public void mostrarTurmas() {
//		
//		
//		usuarioLogin.setTipo("Aluno");
//		fsmservidor.loginServidor();
//		assertEquals(true,( fsmservidor.getState()== StateServer.logado.toString()));
//		assertEquals(usuarioLogin.getTipo(),controleLogin.tentarLogar("Joao", "12345").getTipo());
//		controleTurma.getTurmas("Joao", controleLogin.tentarLogar("Joao", "12345").getTipo());
//		fsmservidor.mostrarTurmas();
//	    assertEquals(true,( fsmservidor.getState()== StateServer.visualizandoTurmas.toString()));
//
//		
//	}
	
	
//	@Test
//	public void escolherTurma() {
//		
//		
//		fsmservidor.mostrarTurmas();
//		assertEquals(true,( fsmservidor.getState()== StateServer.visualizandoTurmas.toString()));
//			if (1 == 1 )
//			{
//			 fsmservidor.escolherTurma();
//			 assertEquals(true,( fsmservidor.getState()== StateServer.turmaEscolhida.toString()));
//			
//			} else assertEquals(true,( fsmservidor.getState()== StateServer.turmaEscolhida.toString()));
//		
//		
//	}
//	
	
//	@Test
//	public void cancelarEscolhaTurma() {
//		
//		
//		fsmservidor.escolherTurma();
//		assertEquals(true,( fsmservidor.getState()== StateServer.turmaEscolhida.toString()));
//			
//			if (1 == 1 )
//			{
//			 fsmservidor.cancelaEscolhaTurma();
//			 assertEquals(true,( fsmservidor.getState()== StateServer.visualizandoTurmas.toString()));
//			
//			} else assertEquals(true,( fsmservidor.getState()== StateServer.visualizandoTurmas.toString()));
//		
//		
//	}
	
	
	@Test
	public void abrirSessaoDaAula() {
			
		
		fsmservidor.loginServidor();

		if (controleLogin.tentarLogar("Eliane", "12345").getTipo() == "Professor")
		{		
			
			if ((Object)controleAula.inicializaChamada(1)!= "NULL" )
			{
			 fsmservidor.entrarEmAula();
			 assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
			
			} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
			
		
	}
	
	
	
	
	
	


}
