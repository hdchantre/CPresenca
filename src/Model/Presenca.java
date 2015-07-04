package Model;

import java.util.Date;

public class Presenca {
	
	private Date diaChamada;
	private Boolean isPresente;
	
	
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
