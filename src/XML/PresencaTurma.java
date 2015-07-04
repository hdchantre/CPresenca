package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PresencaTurma")
public class PresencaTurma {

	private String diaChamada;
	private Boolean isPresente;

	@XmlElement(name = "diaChamada")
	public String getDiaChamada() {
		return diaChamada;
	}

	public void setDiaChamada(String diaChamada) {
		this.diaChamada = diaChamada;
	}

	@XmlElement(name = "isPresente")
	public Boolean getIsPresente() {
		return isPresente;
	}

	public void setIsPresente(Boolean isPresente) {
		this.isPresente = isPresente;
	}

}
