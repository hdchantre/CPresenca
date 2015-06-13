package StateMachines;


import javax.xml.ws.Action;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.*;


public class FSMServidor implements FsmModel {

	// Estados{inativo, logado}
	private String state;
	

	public FSMServidor() {
		this.state = StateServer.inativo.toString();

	}

	@Override
	public Object getState() {
		return String.valueOf(state);
	}

	public void setState(String parm) {
		this.state = parm;
	}

	@Override
	public void reset(boolean bln) {
		state = StateServer.inativo.toString();
	}

	// Condição de Guarda
	// "Para efetuar o login o Aluno deve estar inativo"
	public boolean loginServidorGuard() {

		return state.equals(StateServer.inativo.toString());

	}

	// Ação
	// "Após logar o aluno estra passa pro estado logado"
	public @Action
	void loginServidor() {
		System.out.println("loginAluno: " + state + " --> "
				+ StateServer.logado.toString());
		state = StateServer.logado.toString();
	}

	// Condição de guarda
	// "Para efetuar logout precisa estar logado"
	public boolean loginInvalidoGuard() {
		return state.equals(StateServer.inativo.toString());
	}

	// Ação
	// "Após efetuar logout o aluno fica em estado inativo."
	public @Action
	void loginInvalido() {
		System.out.println("loginInvalido: " + state + " --> "
				+ StateServer.inativo.toString());
				state = StateServer.inativo.toString();
	}
	
	
	// Condição de guarda
	// "Para efetuar logout precisa estar logado"
	public boolean efetuarLogoutGuard() {
		return state.equals(StateServer.logado.toString());
	}

	// Ação
	// "Após efetuar logout o aluno fica em estado inativo."
	public @Action
	void efetuarLogout() {
		System.out.println("efetuarLogout: " + state + " --> "
				+ StateServer.logado.toString());
		state = StateServer.inativo.toString();
	}
	

//	public boolean mostrarTurmasGuard() {
//		return state.equals(StateServer.logado.toString());
//	}
//
//	public @Action
//	void mostrarTurmas() {
//		System.out.println("mostrarTurmas: " + state + " --> "
//				+ StateServer.visualizandoTurmas.toString());
//		state = StateServer.visualizandoTurmas.toString();
//
//	}
//	
//	public boolean escolherTurmaGuard() {
//		return state.equals(StateServer.visualizandoTurmas.toString());
//	}
//
//	public @Action
//	void escolherTurma() {
//		System.out.println("escolherTurma: " + state + " --> "
//				+ StateServer.turmaEscolhida.toString());
//		state = StateServer.turmaEscolhida.toString();
//
//	}
//	
//	
//	public boolean cancelaEscolhaTurmaGuard() {
//		return state.equals(StateServer.turmaEscolhida.toString());
//	}
//
//	public @Action
//	void cancelaEscolhaTurma() {
//		System.out.println("cancelaEscolhaTurma: " + state + " --> "
//				+ StateServer.visualizandoTurmas.toString());
//		state = StateServer.visualizandoTurmas.toString();
//
//	}

	public boolean entrarEmAulaGuard() {
		return state.equals(StateServer.logado.toString());
	}

	public @Action
	void entrarEmAula() {
		System.out.println("entrarEmAula: " + state + " --> "
				+ StateServer.emAula.toString());
		state = StateServer.emAula.toString();

	}

	public boolean computarAulaGuard() {
		return state.equals(StateServer.emAula.toString());
	}

	public @Action
	void computarAula() {
		System.out.println("computarAula: " + state + " --> "
				+ StateServer.computandoAula.toString());
		state = StateServer.computandoAula.toString();

	}
	
	
	public boolean encerrarAulaGuard() {
		return state.equals(StateServer.computandoAula.toString());
	}

	public @Action
	void encerrarAula() {
		System.out.println("encerrarAula: " + state + " --> "
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
		tester.generate(20);

		tester.getModel().printMessage(
				trCoverage.getName() + " was " + trCoverage.toString());
	}

}
