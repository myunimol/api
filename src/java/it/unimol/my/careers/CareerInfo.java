package it.unimol.my.careers;

public class CareerInfo {
	String matricola;
	String tipoCorso;
	String corsoDiStudio;
	String stato;
	String id;
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public void setTipoCorso(String tipoCorso) {
		this.tipoCorso = tipoCorso;
	}
	
	public void setCorsoDiStudio(String corsoDiStudio) {
		this.corsoDiStudio = corsoDiStudio;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public String getMatricola() {
		return matricola;
	}
	public String getTipoCorso() {
		return tipoCorso;
	}
	
	public String getCorsoDiStudio() {
		return corsoDiStudio;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
