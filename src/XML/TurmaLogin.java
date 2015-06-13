package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoginTurma")
public class TurmaLogin {

	private Integer idTurma;
	private String nomeDisciplina;
	private Boolean chamadaAberta;
	
	@XmlElement(name = "nomeDisciplina")
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	@XmlElement(name = "idTurma")
	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	@XmlElement(name = "chamadaAberta")
	public Boolean getChamadaAberta() {
		return chamadaAberta;
	}

	public void setChamadaAberta(Boolean chamadaAberta) {
		this.chamadaAberta = chamadaAberta;
	}

}
