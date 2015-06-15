package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FinalizaAula")
public class FinalizaChamada {

	private boolean isFinalizada;
	private String causaDoProblema;

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
