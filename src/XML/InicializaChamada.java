package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InicializaAula")
public class InicializaChamada {

	private boolean isInicializada;
	private String causaDoProblema;
	private Integer chamadaID;
	private Boolean isPresente;
	
	@XmlElement(name = "isPresente")
	public Boolean getIsPresente() {
		return isPresente;
	}

	public void setIsPresente(Boolean isPresente) {
		this.isPresente = isPresente;
	}

	@XmlElement(name = "chamdaID")
	public Integer getChamadaID() {
		return chamadaID;
	}

	public void setChamadaID(Integer chamadaID) {
		this.chamadaID = chamadaID;
	}

	@XmlElement(name = "isInicializada")
	public boolean isInicializada() {
		return isInicializada;
	}

	public void setInicializada(boolean isInicializada) {
		this.isInicializada = isInicializada;
	}

	@XmlElement(name = "causa")
	public String getCausaDoProblema() {
		return causaDoProblema;
	}

	public void setCausaDoProblema(String causaDoProblema) {
		this.causaDoProblema = causaDoProblema;
	}

}
