package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoginUsuario")
public class UsuarioLogin {
	
	private Boolean sucess;
	private String tipo;
	private Integer chave;
	
	
	@XmlElement(name = "tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlElement(name = "sucess")
	public Boolean getSucess() {
		return sucess;
	}

	public void setSucess(Boolean sucess) {
		this.sucess = sucess;
	}
	
	@XmlElement(name = "chave")
	public Integer getChave() {
		return chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	
}
}