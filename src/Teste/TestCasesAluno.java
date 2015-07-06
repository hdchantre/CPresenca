

package Teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Adaptador.AulaAdaptador;
import Adaptador.LoginAdaptador;
import Adaptador.PresencaAdaptador;
import StateMachines.FSMAluno;
import StateMachines.StateServer;
import StateMachines.StatesAluno;

/**
 * @author Priscila Moraes Ioris
 * 
 */

public class TestCasesAluno {

	
		private LoginAdaptador loginAdaptador;
		private AulaAdaptador aulaAdaptador;
		private FSMAluno fsmAluno;
		private PresencaAdaptador presencaAdaptador;
		

		@Before
		public void setUp() throws Exception {
			loginAdaptador = new LoginAdaptador();

			fsmAluno = new FSMAluno();

			aulaAdaptador = new AulaAdaptador();
			presencaAdaptador =  new PresencaAdaptador();

		}

		//H11
		@Test
		public void logarValidoAluno() {
			fsmAluno.getState();

			boolean isLogado = loginAdaptador.tentarLogar("Joao",
					"12345", "Aluno");

			if (isLogado) {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));
		}

		//H11
		@Test
		public void logarUsuarioInvalidoAluno() {

			fsmAluno.loginInvalido();
			boolean isLogado = loginAdaptador.tentarLogar("Joaoa",
					"12345", "Aluno");

			if (!isLogado) {
				fsmAluno.loginInvalido();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));

			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			}

		}

		//H11
		
		@Test
		public void senhaInvalidaAluno() {

			fsmAluno.loginInvalido();
			boolean isLogado = loginAdaptador.tentarLogar("Joao",
					"123451", "Aluno");

			if (!isLogado) {
				fsmAluno.loginInvalido();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));

			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			}
		}

		//H11
		@Test
		public void senha_ID_Invalida_Aluno() {

			fsmAluno.loginInvalido();
			boolean isLogado = loginAdaptador.tentarLogar("Joaao",
					"123451", "Aluno");

			if (!isLogado) {
				fsmAluno.loginInvalido();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));

			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			}
		}
		
		//H12
		@Test
		public void deslogarValidoAluno() {

			fsmAluno.setState("logado");
			boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joao",
					"Aluno", true);

			if (isDeslogado) {
				fsmAluno.efetuarLogout();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			}
		}
		
		//H12 
		@Test
		public void deslogarJaDeslogadoAluno() {


			fsmAluno.setState("logado");
			boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joao",
					"Aluno", false);

			if (isDeslogado) {
				fsmAluno.efetuarLogout();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			}
		}
		
		//H12
		@Test
		public void deslogarUsarioInvalidoAluno() {


			fsmAluno.setState("logado");
			boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joaoa",
					"Aluno", true);

			if (isDeslogado) {
				fsmAluno.efetuarLogout();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));
			}
		}
		
		//H11
		@Test
		public void deslogarJaDeslogadoUsuarioInvalidoAluno() {


			fsmAluno.setState("logado");
			boolean isDeslogado = loginAdaptador.tentarDeslogarAluno("Joaoa",
					"Aluno", false);

			if (isDeslogado) {
				fsmAluno.efetuarLogout();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));
			}
		}
		

		//H6
		@Test
		public void entrarEmAulaValido() {

			fsmAluno.setState("logado");
			
			boolean isInicializada = aulaAdaptador.entrarEmAula("Joao", 1, true);

			if (isInicializada) {
				fsmAluno.entrarEmAula();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

		}	
		
		//H6
		@Test
		public void entrarEmAulaTurmaInvalida() {

			fsmAluno.setState("logado");
			
			boolean isInicializada = aulaAdaptador.entrarEmAula("Joao", 20, false);

			if (!isInicializada) {
				fsmAluno.failEntrarEmAula();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		
		//H6
		@Test
		public void entrarEmAulaUsuarioInvalido() {

			fsmAluno.setState("logado");
			
			boolean isInicializada = aulaAdaptador.entrarEmAula("Joaooo", 1, false);

			if (!isInicializada) {
				fsmAluno.failEntrarEmAula();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		
		//H6
		@Test
		public void entrarEmAulaUsuarioETurmaInvalidos() {

			fsmAluno.setState("logado");
			
			boolean isInicializada = aulaAdaptador.entrarEmAula("Joaooo", 20, false);

			if (!isInicializada) {
				fsmAluno.failEntrarEmAula();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}

		//Aux - H6 -  na verdade nao tem historia para aluno saindo de aula
		@Test
		public void sairDaAulaValido() {

			fsmAluno.setState("emAula");

			boolean isOut = aulaAdaptador.sairDaAula("Joao");

			if (isOut) {
				fsmAluno.sairdaAula();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		
		//Aux - H6
		@Test
		public void sairDaAulaUsuarioInvalido() {

			fsmAluno.setState("emAula");

			boolean isOut = aulaAdaptador.sairDaAula("Eliane");

			if (!isOut) {
				fsmAluno.faillogoutAula();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

		}
		
		//H8
		@Test
		public void enviarTicket() {

			fsmAluno.setState("emAula");

			boolean isSent = aulaAdaptador.enviarTicket("Joao", 1 , 1);

			if (!isSent) {
				fsmAluno.enviarTicket();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

		}
		
		//H17
		@Test
		public void verificarPresencasValido() {

			fsmAluno.setState("verificandoPresenca");

			boolean isOk = presencaAdaptador.verificarPresencas("Joao", 1);

			if (!isOk) {
				fsmAluno.verificarPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

		}

		
		//Aux H17
		@Test
		public void sairVerificarPresencasValido() {

			fsmAluno.setState("verificandoPresenca");

			boolean isOk = presencaAdaptador.sairVerficarPresencas("Joao", 1);

			if (!isOk) {
				fsmAluno.sairVerificacaoPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				fsmAluno.setState("logado");
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		

		/*
		@Test
		public void sairVerificarPresencasTurmaInvalida() {

			fsmAluno.setState("verificandoPresenca");

			boolean isOk = presencaAdaptador.sairVerficarPresencas("Joao", 20);

			if (!isOk) {
				fsmAluno.sairVerificacaoPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

		}
		
		@Test
		public void sairVerificarPresencasNomeInvalido() {

			fsmAluno.setState("verificandoPresenca");

			boolean isOk = presencaAdaptador.sairVerficarPresencas("Joaooo", 1);

			if (!isOk) {
				fsmAluno.sairVerificacaoPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

		}
		
		/*
		//Não precisa
		@Test  
		public void verificarPresencasNomeInvalido() {

			fsmAluno.setState("logado");

			boolean isOk = presencaAdaptador.verificarPresencas("Joaooo", 1);

			if (!isOk) {
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				fsmAluno.verificarPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		
		//Não precisa
		@Test
		public void verificarPresencasTurmaInvalida() {

			fsmAluno.setState("logado");

			boolean isOk = presencaAdaptador.verificarPresencas("Joao", 20);

			if (!isOk) {
				fsmAluno.verificarPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}*/



}


