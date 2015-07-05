package Model;

import java.sql.Timestamp;
import java.util.Date;

public class Presenca {

	private Date diaChamada;
	private Boolean isPresente;
	private Timestamp horaFim;
	private Timestamp horaInicio;
	private Integer porPresenca;

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Timestamp getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Timestamp horaFim) {
		this.horaFim = horaFim;
	}

	public Integer getPorPresenca() {
		return porPresenca;
	}

	public void setPorPresenca(Integer porPresenca) {
		this.porPresenca = porPresenca;
	}

	public Date getDiaChamada() {
		return diaChamada;
	}

	public void setDiaChamada(Date diaChamada) {
		this.diaChamada = diaChamada;
	}

	public Boolean getIsPresente() {
		return isPresente;
	}

	public void setIsPresente(Boolean isPresente) {
		this.isPresente = isPresente;
	}

}
