package XML;

import java.sql.Time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChamadaInfo")
public class ChamadaInfo {

	private String horaFim;
	private String horaIni;
	private Integer porPresenca;

	@XmlElement(name = "horaFim")
	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	@XmlElement(name = "horaIni")
	public String getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	@XmlElement(name = "porPresenca")
	public Integer getPorPresenca() {
		return porPresenca;
	}

	public void setPorPresenca(Integer porPresenca) {
		this.porPresenca = porPresenca;
	}

}
