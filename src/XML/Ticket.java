package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ticket")
public class Ticket {

	private boolean ticketRecebido;
	private boolean fimAula;

	@XmlElement(name = "isRecebido")
	public boolean isTicketRecebido() {
		return ticketRecebido;
	}

	public void setTicketRecebido(boolean ticketRecebido) {
		this.ticketRecebido = ticketRecebido;
	}

	@XmlElement(name = "acabouAula")
	public boolean isFimAula() {
		return fimAula;
	}

	public void setFimAula(boolean fimAula) {
		this.fimAula = fimAula;
	}
}
