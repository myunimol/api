package it.unimol.my.exam;

import java.util.Date;

/**
 * Questa classe fornisce una astrazione dell'esame. In particolare conserva le
 * informazioni relative alla materia,al numero di CFU,al voto, alla data di
 * superamento dell'esame e l'anno accademico.
 * 
 * @author Christian De Rita
 */
public class Exam {

	/**
	 * Nome dell'esame
	 */
	private String name;
	/**
	 * Peso in cfu dell'esame
	 */
	private int cfu;
	/**
	 * Voto conseguito all'esame
	 */
	private String vote;
	/**
	 * Data di superamento dell'esame
	 */
	private Date date;
	/**
	 * Anno accademico durante il quale è stato seguito il corso
	 */
	private String year;
	/**
	 * Id dell'esame
	 */
	private String id;

	/**
	 * Costruttore completo per la creazione di un oggetto Exam. Prende in input
	 * tutte le informazioni disponibili dalla pagina della lista degli esami.
	 * 
	 * @param name
	 *            Materia dell'esame
	 * @param cfu
	 *            Peso in cfu dell'esame
	 * @param grade
	 *            Voto esame
	 * @param date
	 *            Data superamento esame
	 * @param academicYear
	 *            Anno accademico durante il quale è stato frequentato l'esame
	 * @param adsceId
	 *            L'id dell'esame
	 */
	public Exam(String name, int cfu, String grade, Date date,
			String academicYear, String adsceId) {
		this.name = name;
		this.cfu = cfu;
		this.vote = grade;
		this.date = date;
		this.year = academicYear;
		this.id = adsceId;
	}

	/**
	 * Costruttore per la creazione di un oggetto Exam senza parametri.
	 */
	public Exam() {

	}

	/**
	 * Restituisce il nome dell'esame
	 * 
	 * @return il nome dell'esame come stringa
	 */
	public String getName() {
		return name;
	}

	/**
	 * Imposta il nome dell'esame
	 * 
	 * @param name
	 *            il nome da impostare
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Restituisce il numero dei cfu dell'esame
	 * 
	 * @return il numero dei cfu
	 */
	public int getCfu() {
		return cfu;
	}

	/**
	 * Imposta il numero del peso in cfu dell'esame
	 * 
	 * @param cfu
	 *            I cfu dell'esame
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	/**
	 * Restituisce il voto conseguito
	 * 
	 * @return Voto conseguito come stringa
	 */
	public String getGrade() {
		return vote;
	}

	/**
	 * Imposta il voto conseguito all' esame
	 * 
	 * @param grade
	 *            voto conseguito all' esame
	 */
	public void setGrade(String grade) {
		this.vote = grade;
	}

	/**
	 * Restituisce la data di superamento dell'esame
	 * 
	 * @return la data di supermaneto dell'esame
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Imposta la data di superamento dell'esame
	 * 
	 * @param date
	 *            la data
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Restituisce l'anno in cui è stato seguita la materia d'esame
	 * 
	 * @return Anno
	 */
	public String getAcademicYear() {
		return year;
	}

	/**
	 * Imposta l'anno in cui è stata seguita la materia d'esame
	 * 
	 * @param academicYear
	 *            l'anno accademico da settare
	 */
	public void setAcademicYear(String academicYear) {
		this.year = academicYear;
	}

	/**
	 * Restituisce l'id dell'esame
	 * 
	 * @return the adsceId L'id dell'esame
	 */
	public String getAdsceId() {
		return id;
	}

	/**
	 * Imposta l'id dell'esame
	 * 
	 * @param adsceId
	 *            L'id dell'esame
	 */
	public void setAdsceId(String adsceId) {
		this.id = adsceId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exam [name=" + name + ", cfu=" + cfu + ", grade=" + vote
				+ ", date=" + date + ", academicYear=" + year + ", adsceId="
				+ id + "]";
	}

}
