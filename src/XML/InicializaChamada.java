package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InicializaAula")
public class InicializaChamada {

	private boolean isInicializada;
	private String causaDoProblema;

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
