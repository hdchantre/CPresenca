package Teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Adaptador.AulaAdaptador;
import Adaptador.LoginAdaptador;
import StateMachines.FSMServidor;
import StateMachines.StateServer;

public class TestCasesAluno {

	
		private LoginAdaptador loginAdaptador;
		private AulaAdaptador auladaptador;
		private FSMServidor fsmAluno;
		

		@Before
		public void setUp() throws Exception {
			loginAdaptador = new LoginAdaptador();

			fsmAluno = new FSMServidor();

			auladaptador = new AulaAdaptador();

		}

		@Test
		public void logarValidoAluno() {
			fsmAluno.getState();

			boolean isLogado = loginAdaptador.tentarLogar("Joao",
					"12345", "Aluno");

			if (isLogado) {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StateServer.logado.toString()));
		}

		@Test
		public void logarUsuarioInvalidoAluno() {

			fsmAluno.loginInvalido();
			boolean isLogado = loginAdaptador.tentarLogar("Joaoa",
					"12345", "Aluno");

			if (!isLogado) {
				fsmAluno.loginInvalido();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.inativo.toString()));

			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.inativo.toString()));
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
						(fsmAluno.getState() == StateServer.inativo.toString()));

			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.inativo.toString()));
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
						(fsmAluno.getState() == StateServer.inativo.toString()));

			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.inativo.toString()));
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
						(fsmAluno.getState() == StateServer.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.inativo.toString()));
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
						(fsmAluno.getState() == StateServer.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.inativo.toString()));
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
						(fsmAluno.getState() == StateServer.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.logado.toString()));
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
						(fsmAluno.getState() == StateServer.inativo.toString()));
			} else {
				fsmAluno.loginServidor();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.logado.toString()));
			}
		}
		

		@Test
		public void entrarEmAula() {

			fsmAluno.setState("logado");
			
			boolean isInicializada = auladaptador.entrarEmAula(1, true);

			if (isInicializada) {
				fsmAluno.entrarEmAula();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.emAula.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StateServer.emAula.toString()));

		}

		
		@Test
		public void sairDaAula() {

			fsmAluno.setState("emAula");

			boolean isOut = auladaptador.sairDaAula(1);

			if (isOut) {
				fsmAluno.sairdaAula();
				assertEquals(true,
						(fsmAluno.getState() == StateServer.logado.toString()));

			} else
				assertEquals(true,
						(fsmAluno.getState() == StateServer.logado.toString()));

		}

	}


