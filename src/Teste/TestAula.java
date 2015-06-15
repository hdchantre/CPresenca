package Teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Control.ControleAula;
import Control.ControleLogin;
import Model.Chamada;
import Model.Usuario;
import StateMachines.FSMServidor;
import StateMachines.StateServer;

public class TestAula {

	private ControleLogin controleLogin;
    private Usuario usuario;
	private FSMServidor  fsmservidor;
	
	private ControleAula controleAula;
//	private boolean IsLogado; 
	public int chave;
	@Before
	public void setUp() throws Exception {
		controleLogin = new ControleLogin();
//		usuarioLogin = new UsuarioLogin();	
		fsmservidor = new FSMServidor();
	//	controleTurma = new ControleTurma();
		controleAula = new ControleAula();
		usuario = new Usuario();
	}
	
	@Test
	public void abrirSessaoDaAula() {
			
	
		fsmservidor.loginServidor();
		
		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);
			
		if (chamadaTeste.getChamadaAberta())
		{
			fsmservidor.entrarEmAula();
			assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		
	}
	

	@Test
	public void abrirSessaoDaAulaFail() {
			
	
		fsmservidor.loginServidor();
		
		Chamada chamadaTeste = controleAula.inicializaChamada("Joao", 1);
			
		if (chamadaTeste.getChamadaAberta())
		{
			fsmservidor.entrarEmAula();
			assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		
	}
	
	//Abrir sessão ja aberta
	@Test
	public void abrirSessaoJaAberta() {
			
	
		fsmservidor.loginServidor();
		
		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);
			
		if (chamadaTeste == null)
		{
			fsmservidor.entrarEmAula();
			assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		
	}
	
}
