package Model;

import java.util.Date;

public class Aluno extends Usuario {

	private Date anoAdmissao;
	private String curso;
	private String tipoAluno;
	private Integer idChamada;
	

	public Integer getIdChamada() {
		return idChamada;
	}

	public void setIdChamada(Integer idChamada) {
		this.idChamada = idChamada;
	}

	public Date getAnoAdmissao() {
		return anoAdmissao;
	}

	public void setAnoAdmissao(Date anoAdmissao) {
		this.anoAdmissao = anoAdmissao;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTipoAluno() {
		return tipoAluno;
	}

	public void setTipoAluno(String tipoAluno) {
		this.tipoAluno = tipoAluno;
	}
}
