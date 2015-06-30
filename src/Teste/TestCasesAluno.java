package Teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Adaptador.AlunoAdaptador;
import Adaptador.AulaAdaptador;
import Adaptador.LoginAdaptador;
import StateMachines.FSMAluno;
import StateMachines.StateServer;
import StateMachines.StatesAluno;

public class TestCasesAluno {

	
		private LoginAdaptador loginAdaptador;
		private AulaAdaptador aulaAdaptador;
		private FSMAluno fsmAluno;
		private AlunoAdaptador alunoAdaptador;
		

		@Before
		public void setUp() throws Exception {
			loginAdaptador = new LoginAdaptador();

			fsmAluno = new FSMAluno();

			aulaAdaptador = new AulaAdaptador();
			alunoAdaptador =  new AlunoAdaptador();

		}

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
		
		@Test
		public void enviarTicket() {

			fsmAluno.setState("emAula");

			boolean isSent = aulaAdaptador.enviarTicket("Joao");

			if (!isSent) {
				fsmAluno.enviarTicket();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.emAula.toString()));

		}
		
		@Test
		public void verificarPresencasValido() {

			fsmAluno.setState("verificandoPresenca");

			boolean isOk = alunoAdaptador.verificarPresencas("Joao", 1);

			if (!isOk) {
				fsmAluno.verificarPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

		}
		
		//N�o precisa
		@Test
		public void verificarPresencasNomeInvalido() {

			fsmAluno.setState("logado");

			boolean isOk = alunoAdaptador.verificarPresencas("Joaooo", 1);

			if (!isOk) {
				fsmAluno.verificarPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		
		//N�o precisa
		@Test
		public void verificarPresencasTurmaInvalida() {

			fsmAluno.setState("logado");

			boolean isOk = alunoAdaptador.verificarPresencas("Joao", 20);

			if (!isOk) {
				fsmAluno.verificarPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		
		@Test
		public void sairVerificarPresencasValido() {

			fsmAluno.setState("verificandoPresenca");

			boolean isOk = alunoAdaptador.sairVerficarPresencas("Joao", 1);

			if (!isOk) {
				fsmAluno.sairVerificacaoPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.logado.toString()));

		}
		
		@Test
		public void sairVerificarPresencasTurmaInvalida() {

			fsmAluno.setState("verificandoPresenca");

			boolean isOk = alunoAdaptador.sairVerficarPresencas("Joao", 20);

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

			boolean isOk = alunoAdaptador.sairVerficarPresencas("Joaooo", 1);

			if (!isOk) {
				fsmAluno.sairVerificacaoPresencas();
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StatesAluno.verificandoPresenca.toString()));

		}
		

	}


