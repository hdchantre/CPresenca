package StateMachines;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.*;

public class FSMAluno implements FsmModel{

		// Estados{inativo, logado}
		private String state;
		

		public FSMAluno() {
			this.state = StateServer.inativo.toString();

		}

		public Object getState() {
			return String.valueOf(state);
		}

		public void setState(String parm) {
			this.state = parm;
		}


		public void reset(boolean bln) {
			state = StateServer.inativo.toString();
		}

		// Condi��o de Guarda
		// "Para efetuar o login o Aluno deve estar inativo"
		public boolean loginServidorGuard() {

			return state.equals(StateServer.inativo.toString());

		}

		// A��o
		// "Ap�s logar o aluno estra passa pro estado logado"
		public @Action
		void loginServidor() {
			System.out.println("loginServidor: " + state + " --> "
					+ StateServer.logado.toString());
			state = StateServer.logado.toString();
		}

		// Condi��o de guarda
		// "Para efetuar logout precisa estar logado"
		public boolean loginInvalidoGuard() {
			return state.equals(StateServer.inativo.toString());
		}

		// A��o
		// "Ap�s efetuar logout o aluno fica em estado inativo."
		public @Action
		void loginInvalido() {
			System.out.println("loginInvalido: " + state + " --> "
					+ StateServer.inativo.toString());
					state = StateServer.inativo.toString();
		}
		
		
		// Condi��o de guarda
		// "Para efetuar logout precisa estar logado"
		public boolean efetuarLogoutGuard() {
			return state.equals(StateServer.logado.toString());
		}

		// A��o
		// "Ap�s efetuar logout o aluno fica em estado inativo."
		public @Action
		void efetuarLogout() {
			System.out.println("efetuarLogout: " + state + " --> "
					+ StateServer.inativo.toString());
			state = StateServer.inativo.toString();
		}
		

//		public boolean mostrarTurmasGuard() {
//			return state.equals(StateServer.logado.toString());
//		}
	//
//		public @Action
//		void mostrarTurmas() {
//			System.out.println("mostrarTurmas: " + state + " --> "
//					+ StateServer.visualizandoTurmas.toString());
//			state = StateServer.visualizandoTurmas.toString();
	//
//		}
	//	
//		public boolean escolherTurmaGuard() {
//			return state.equals(StateServer.visualizandoTurmas.toString());
//		}
	//
//		public @Action
//		void escolherTurma() {
//			System.out.println("escolherTurma: " + state + " --> "
//					+ StateServer.turmaEscolhida.toString());
//			state = StateServer.turmaEscolhida.toString();
	//
//		}
	//	
	//	
//		public boolean cancelaEscolhaTurmaGuard() {
//			return state.equals(StateServer.turmaEscolhida.toString());
//		}
	//
//		public @Action
//		void cancelaEscolhaTurma() {
//			System.out.println("cancelaEscolhaTurma: " + state + " --> "
//					+ StateServer.visualizandoTurmas.toString());
//			state = StateServer.visualizandoTurmas.toString();
	//
//		}

		public boolean entrarEmAulaGuard() {
			return state.equals(StateServer.logado.toString());
		}

		public @Action
		void entrarEmAula() {
			System.out.println("entrarEmAula: " + state + " --> "
					+ StateServer.emAula.toString());
			state = StateServer.emAula.toString();

		}

		public boolean failEntrarEmAulaGuard() {
			return state.equals(StateServer.logado.toString());
		}

		public @Action
		void failEntrarEmAula() {
			System.out.println("FailEntrarEmAula: " + state + " --> "
					+ StateServer.logado.toString());
			state = StateServer.logado.toString();

		}
		public boolean faillogoutAulaGuard() {
			return state.equals(StateServer.emAula.toString());
		}

		public @Action
		void faillogoutAula() {
			System.out.println("faillogoutAula: " + state + " --> "
					+ StateServer.emAula.toString());
			state = StateServer.emAula.toString();

		}
		
		
		public boolean sairdaAulaGuard() {
			return state.equals(StateServer.emAula.toString());
		}

		public @Action
		void sairdaAula() {
			System.out.println("sairdaAula: " + state + " --> "
					+ StateServer.logado.toString());
			state = StateServer.logado.toString();

		}
		
		

		@SuppressWarnings("deprecation")
		public static void main(String args[]) {
			// create our model and a test generation algorithm
			Tester tester = new GreedyTester(new FSMServidor());

			// build the complete FSM graph for our model, just to ensure
			// that we get accurate model coverage metrics.
			tester.buildGraph();

			// set up our favourite coverage metric
			CoverageMetric trCoverage = new TransitionCoverage();
			tester.addCoverageMetric(trCoverage);

			// ask to print the generated tests
			tester.addListener("verbose", new VerboseListener(tester.getModel()));

			// generate a small test suite of 20 steps (covers 4/5 transitions)
			tester.generate(30);

			tester.getModel().printMessage(
					trCoverage.getName() + " was " + trCoverage.toString());
		}


}
