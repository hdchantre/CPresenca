package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FinalizaAula")
public class FinalizaChamada {

	private boolean isFinalizada;
	private String causaDoProblema;
	private String Aluno;
	private Boolean presenca;

	@XmlElement(name = "Aluno")
	public String getAluno() {
		return Aluno;
	}

	public void setAluno(String aluno) {
		Aluno = aluno;
	}

	@XmlElement(name = "isPresente")
	public Boolean getPresenca() {
		return presenca;
	}

	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}

	@XmlElement(name = "isFinalizada")
	public boolean isFinalizada() {
		return isFinalizada;
	}

	public void setFinalizada(boolean isFinalizada) {
		this.isFinalizada = isFinalizada;
	}

	@XmlElement(name = "causa")
	public String getCausaDoProblema() {
		return causaDoProblema;
	}

	public void setCausaDoProblema(String causaDoProblema) {
		this.causaDoProblema = causaDoProblema;
	}

}
