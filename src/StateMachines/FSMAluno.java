package StateMachines;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.*;

public class FSMAluno implements FsmModel{

		// Estados{inativo, logado}
		private String state;
		

		public FSMAluno() {
			this.state = StatesAluno.inativo.toString();

		}

		public Object getState() {
			return String.valueOf(state);
		}

		public void setState(String parm) {
			this.state = parm;
		}


		public void reset(boolean bln) {
			state = StatesAluno.inativo.toString();
		}

		// Condição de Guarda
		// "Para efetuar o login o Aluno deve estar inativo"
		public boolean loginServidorGuard() {

			return state.equals(StatesAluno.inativo.toString());

		}

		//H11
		// Ação
		// "Após logar o aluno estra passa pro estado logado"
		public @Action
		void loginServidor() {
			System.out.println("loginServidor: " + state + " --> "
					+ StatesAluno.logado.toString());
			state = StatesAluno.logado.toString();
		}

		// Condição de guarda
		// "Para efetuar logout precisa estar logado"
		public boolean loginInvalidoGuard() {
			return state.equals(StatesAluno.inativo.toString());
		}

		// Ação
		// "Após efetuar logout o aluno fica em estado inativo."
		public @Action
		void loginInvalido() {
			System.out.println("loginInvalido: " + state + " --> "
					+ StatesAluno.inativo.toString());
					state = StatesAluno.inativo.toString();
		}
		
		
		// Condição de guarda
		// "Para efetuar logout precisa estar logado"
		public boolean efetuarLogoutGuard() {
			return state.equals(StatesAluno.logado.toString());
		}

		//H12
		// Ação
		// "Após efetuar logout o aluno fica em estado inativo."
		public @Action
		void efetuarLogout() {
			System.out.println("efetuarLogout: " + state + " --> "
					+ StatesAluno.inativo.toString());
			state = StatesAluno.inativo.toString();
		}
		

		public boolean entrarEmAulaGuard() {
			return state.equals(StatesAluno.logado.toString());
		}

		//H4
		public @Action
		void entrarEmAula() {
			System.out.println("entrarEmAula: " + state + " --> "
					+ StatesAluno.emAula.toString());
			state = StatesAluno.emAula.toString();

		}

		public boolean verificarPresencasGuard() {
			return state.equals(StatesAluno.logado.toString());
		}

		//H17
		public @Action
		void verificarPresencas() {
			System.out.println("verificacaoPresencas: " + state + " --> "
					+ StatesAluno.verificandoPresenca.toString());
			state = StatesAluno.verificandoPresenca.toString();

		}
		
		public boolean sairVerificacaoPresencasGuard() {
			return state.equals(StatesAluno.verificandoPresenca.toString());
		}

		public @Action
		void sairVerificacaoPresencas() {
			System.out.println("verificacaoPresencas: " + state + " --> "
					+ StatesAluno.logado.toString());
			state = StatesAluno.logado.toString();

		}		
		
		
		public boolean failEntrarEmAulaGuard() {
			return state.equals(StatesAluno.logado.toString());
		}

		public @Action
		void failEntrarEmAula() {
			System.out.println("FailEntrarEmAula: " + state + " --> "
					+ StatesAluno.logado.toString());
			state = StatesAluno.logado.toString();

		}
		public boolean faillogoutAulaGuard() {
			return state.equals(StatesAluno.emAula.toString());
		}

		public @Action
		void faillogoutAula() {
			System.out.println("faillogoutAula: " + state + " --> "
					+ StatesAluno.emAula.toString());
			state = StatesAluno.emAula.toString();

		}
		
		public boolean enviarTicketGuard() {
			return state.equals(StatesAluno.emAula.toString());
		}

		//H8
		public @Action
		void enviarTicket() {
			System.out.println("enviarTicket: " + state + " --> "
					+ StatesAluno.emAula.toString());
			state = StatesAluno.emAula.toString();

		}		
		
		public boolean sairdaAulaGuard() {
			return state.equals(StatesAluno.emAula.toString());
		}

		// sem historia
		public @Action
		void sairdaAula() {
			System.out.println("sairdaAula: " + state + " --> "
					+ StatesAluno.logado.toString());
			state = StatesAluno.logado.toString();

		}
		
		

		@SuppressWarnings("deprecation")
		public static void main(String args[]) {
			// create our model and a test generation algorithm
			Tester tester = new GreedyTester(new FSMAluno());

			// build the complete FSM graph for our model, just to ensure
			// that we get accurate model coverage metrics.
			tester.buildGraph();

			// set up our favourite coverage metric
			CoverageMetric trCoverage = new TransitionCoverage();
			tester.addCoverageMetric(trCoverage);

			// ask to print the generated tests
			tester.addListener("verbose", new VerboseListener(tester.getModel()));

			// generate a small test suite of 20 steps (covers 4/5 transitions)
			tester.generate(20);

			tester.getModel().printMessage(
					trCoverage.getName() + " was " + trCoverage.toString());
		}


}
