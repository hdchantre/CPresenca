package Model;

import java.sql.Time;
import java.util.Date;
import java.util.Map;

public class Chamada {

	private Integer id;
	private Turma turma;
	private Date dataChamada;
	private Time inicio;
	private Time fim;
	private Map<Aluno, Boolean> alunos;
	private Boolean chamadaAberta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Date getDataChamada() {
		return dataChamada;
	}

	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
	}

	public Time getInicio() {
		return inicio;
	}

	public void setInicio(Time inicio) {
		this.inicio = inicio;
	}

	public Time getFim() {
		return fim;
	}

	public void setFim(Time fim) {
		this.fim = fim;
	}

	public Map<Aluno, Boolean> getAlunos() {
		return alunos;
	}

	public void setAlunos(Map<Aluno, Boolean> alunos) {
		this.alunos = alunos;
	}

	public Boolean getChamadaAberta() {
		return chamadaAberta;
	}

	public void setChamadaAberta(Boolean chamadaAberta) {
		this.chamadaAberta = chamadaAberta;
	}

}
