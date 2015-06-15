package Teste;
//import org.junit.FixMethodOrder;
import static org.junit.Assert.assertEquals;
//import org.junit.runners.MethodSorters;

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
	private Usuario usuario;
	private FSMServidor fsmservidor;
	private ControleAula controleAula;

	@Before
	public void setUp() throws Exception {
		controleLogin = new ControleLogin();

		fsmservidor = new FSMServidor();

		controleAula = new ControleAula();
		usuario = new Usuario();
	}

	
	@Test
	public void a_logarValido() {
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
	public void g_deslogarValido() {

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

	@Test
	public void c_logarInvalido() {

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
	public void d_senhaInvalida() {

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
	public void e_senha_ID_Invalida() {

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

	// @Test
	// public void mostrarTurmas() {
	//		
	//		
	// usuarioLogin.setTipo("Aluno");
	// fsmservidor.loginServidor();
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.logado.toString()));
	// assertEquals(usuarioLogin.getTipo(),controleLogin.tentarLogar("Joao",
	// "12345").getTipo());
	// controleTurma.getTurmas("Joao", controleLogin.tentarLogar("Joao",
	// "12345").getTipo());
	// fsmservidor.mostrarTurmas();
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.visualizandoTurmas.toString()));
	//
	//		
	// }

	// @Test
	// public void escolherTurma() {
	//		
	//		
	// fsmservidor.mostrarTurmas();
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.visualizandoTurmas.toString()));
	// if (1 == 1 )
	// {
	// fsmservidor.escolherTurma();
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.turmaEscolhida.toString()));
	//			
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.turmaEscolhida.toString()));
	//		
	//		
	// }
	//	

	// @Test
	// public void cancelarEscolhaTurma() {
	//		
	//		
	// fsmservidor.escolherTurma();
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.turmaEscolhida.toString()));
	//			
	// if (1 == 1 )
	// {
	// fsmservidor.cancelaEscolhaTurma();
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.visualizandoTurmas.toString()));
	//			
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.visualizandoTurmas.toString()));
	//		
	//		
	// }

	// @Test
	// public void abrirSessaoDaAula() {
	//			
	//		
	// fsmservidor.loginServidor();
	//
	// if (controleLogin.tentarLogar("Eliane", "12345").getTipo() ==
	// "Professor")
	// {
	//			
	// if ((Object)controleAula.inicializaChamada(1)!= "NULL" )
	// {
	// fsmservidor.entrarEmAula();
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString()));
	//			
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString()));
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString()));
	//			
	//		
	// }

	
	@Test
	public void f_abrirSessaoDaAula() {
			
	
		fsmservidor.setState("logado");
		
		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);
		//System.out.println("chamadaTeste"+ chamadaTeste);
		
				
		if (chamadaTeste.getChamadaAberta())
		{
			fsmservidor.entrarEmAula();
			assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		
	}
	
	
		
	
	@Test
	public void e_abrirSessaoJaAberta() {
			
	
		fsmservidor.setState("logado");
		
		Chamada chamadaTeste = controleAula.inicializaChamada("Eliane", 1);
			
		if (chamadaTeste == null)
		{
			fsmservidor.entrarEmAula();
			assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
		
		
	}

	// Basta estar em aula para que a secao seja fechada
	// Se houver como testar se é a professora que esta tentando fechar a aula
	// seria bacana

	// @Test
	// public void computarSecaoAulaValida() {
	//					
	// Integer idValido = 1;
	//		
	// fsmservidor.entrarEmAula();//está certo isso?
	//
	// if (fsmservidor.getState() == StateServer.emAula.toString())
	// {
	//			
	// if (controleAula.computarAula(idValido))
	// {
	// fsmservidor.computarAula();
	//			 
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.computandoAula.toString()));
	//			
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString()));
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString()));
	//				
	// }
	//	
	// @Test
	// public void computarSecaoAulaInvalida() {
	//					
	// Integer idInvalido = 10;
	//		
	// fsmservidor.entrarEmAula();//está certo isso?
	//
	// if (fsmservidor.getState() == StateServer.emAula.toString())
	// {
	//			
	// if (controleAula.computarAula(idInvalido))
	// {
	//				
	// assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString())); //nao deve sair da aul
	//			
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString()));
	// } else assertEquals(true,( fsmservidor.getState()==
	// StateServer.emAula.toString()));
	//				
	// }
	//	
	//
	
	/*@Test
	public void fecharSecaoAula() {
						
		fsmservidor.encerrarAula();
	
		if (controleAula.fecharAula()){
	 
			assertEquals(true,( fsmservidor.getState() == StateServer.logado.toString()));
				
		} else assertEquals(true,( fsmservidor.getState() == StateServer.computandoAula.toString()));
					
	 }*/
	

}
