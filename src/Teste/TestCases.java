package Teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Control.ControleAula;
import Control.ControleLogin;
//import Control.ControleTurma;
import StateMachines.FSMServidor;
import StateMachines.StateServer;
import Model.Usuario;


public class TestCases {
	
	private ControleLogin controleLogin;
    private Usuario usuario;
	private FSMServidor  fsmservidor;
	//private ControleTurma controleTurma;
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
	public void logarValido() {
		
		Usuario usuario = controleLogin.tentarLogar("Joao", "12345");
		chave = usuario.getChave();
		  
		if (usuario.getIsLogado())
		{
			fsmservidor.loginServidor();
			assertEquals(true,( fsmservidor.getState()== StateServer.logado.toString()));
			
			
			

			
		}
		else assertEquals(true,( fsmservidor.getState()== StateServer.logado.toString()));
		
		
	}
	
	
	@Test
	public void deslogarValido() {
		
		fsmservidor.loginServidor();
		Usuario usuario = controleLogin.tentarDeslogar("Joao", "Aluno", chave);
			
		  
		if (usuario.getIsLogado())
		{
			fsmservidor.efetuarLogout();
			assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
			
				
			
		}
		else assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
		
		
	}
	
	
	/*@Test
	public void logarInvalido() {
		
		if (controleLogin.tentarLogar("Joao", "12345").getIsLogado()== false)
		{
			fsmservidor.loginInvalido();
			assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
		}
		else assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
		

		
	}*/
	
	
	
//	@Test
//	public void senhaInvalida() {
//		
//		assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
//		usuarioLogin.setSucess(false);
//		fsmservidor.loginInvalido();
//		assertEquals(usuarioLogin.getSucess(),controleLogin.tentarLogar("Joao", "123456").getSucess());
//	    assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
//
//		
//	}
	
//	@Test
//	public void senha_ID_Invalida() {
//		
//		assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
//		usuarioLogin.setSucess(false);
//		fsmservidor.loginInvalido();
//		assertEquals(usuarioLogin.getSucess(),controleLogin.tentarLogar("Daniela", "12345").getSucess());
//	    assertEquals(true,( fsmservidor.getState()== StateServer.inativo.toString()));
//
//		
//	}
	
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
	
	
//	@Test
//	public void abrirSessaoDaAula() {
//			
//		
//		fsmservidor.loginServidor();
//
//		if (controleLogin.tentarLogar("Eliane", "12345").getTipo() == "Professor")
//		{		
//			
//			if ((Object)controleAula.inicializaChamada(1)!= "NULL" )
//			{
//			 fsmservidor.entrarEmAula();
//			 assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//			
//			} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//			
//		
//	}
	
	//Nao deve passar, pois o João é aluno e não professor
//	@Test
//	public void abrirSessaoDaAulaAluno() {
//					
//		fsmservidor.loginServidor();
//
//		if (controleLogin.tentarLogar("Joao", "12345").getTipo() == "Professor")
//		{		
//			
//			if ((Object)controleAula.inicializaChamada(1)!= "NULL" )
//			{
//			 fsmservidor.entrarEmAula();
//			 assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//			
//			} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//			
//		
//	}
	
	//Basta estar em aula para que a secao seja fechada
	//Se houver como testar se é a professora que esta tentando fechar a aula seria bacana
	
//	@Test
//	public void computarSecaoAulaValida() {
//					
//		Integer idValido = 1;
//		
//		fsmservidor.entrarEmAula();//está certo isso?
//
//		if (fsmservidor.getState() == StateServer.emAula.toString())
//		{		
//			
//			if (controleAula.computarAula(idValido))
//			{
//				fsmservidor.computarAula();
//			 
//				assertEquals(true,( fsmservidor.getState()== StateServer.computandoAula.toString()));
//			
//			} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//				
//	}
//	
//	@Test
//	public void computarSecaoAulaInvalida() {
//					
//		Integer idInvalido = 10;
//		
//		fsmservidor.entrarEmAula();//está certo isso?
//
//		if (fsmservidor.getState() == StateServer.emAula.toString())
//		{		
//			
//			if (controleAula.computarAula(idInvalido))
//			{
//				
//				assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString())); //nao deve sair da aul
//			
//			} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//		} else assertEquals(true,( fsmservidor.getState()== StateServer.emAula.toString()));
//				
//	}
//	
//
//	@Test
//	public void fecharSecaoAula() {
//					
//		fsmservidor.computarAula();//está certo isso?
//
//		if (fsmservidor.getState() == StateServer.computandoAula.toString())
//		{		
//			
//			if (controleAula.fecharAula())
//			{
//				//Depois que estiver gerando os relatorios aqui deve ser testado se o relatorio 
//				//foi gerado corretamente
//				fsmservidor.encerrarAula();
//			 
//				assertEquals(true,( fsmservidor.getState()== StateServer.logado.toString()));
//			
//			} else assertEquals(true,( fsmservidor.getState()== StateServer.computandoAula.toString()));
//		} else assertEquals(true,( fsmservidor.getState()== StateServer.computandoAula.toString()));
//				
//	}
	
	


}
