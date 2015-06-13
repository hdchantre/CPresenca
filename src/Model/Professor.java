package Model;

import java.util.Date;

public class Professor extends Usuario {

	private Date anoAdmissao;
	private String nivel;
	private String departamento;

	public Date getAnoAdmissao() {
		return anoAdmissao;
	}

	public void setAnoAdmissao(Date anoAdmissao) {
		this.anoAdmissao = anoAdmissao;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}
