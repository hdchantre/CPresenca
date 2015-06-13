package Model;

import java.util.Date;
import java.util.List;

public class Turma {

	private Integer id;
	private Professor professor;
	private Disciplina disciplina;
	private Date dataInicio;
	private Date dataFim;
	private List<Aluno> listaAluno;
	private Boolean chamadaAberta;

	public Boolean getChamadaAberta() {
		return chamadaAberta;
	}

	public void setChamadaAberta(Boolean chamadaAberta) {
		this.chamadaAberta = chamadaAberta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Aluno> getListaAluno() {
		return listaAluno;
	}

	public void setListaAluno(List<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}

	public void addAluno(Aluno aluno) {
		this.listaAluno.add(aluno);
	}

	public void removeAluno(Aluno aluno) {
		this.listaAluno.remove(aluno);
	}

}
