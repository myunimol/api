package it.unimol.my.elencoappelli;

import java.util.Date;

/**
 * Questa classe fornisce una astrazione dell'appello d'esame. In particolare
 * conserva le informazioni relative al nome dell'esame, CFU, professore, data,
 * data di fine possibilita' di prenotazione, aula e note della'appello.
 * 
 * @author Giuseppe Bianco
 *
 */
public class ExamSession {

	/**
	 * Descrizione variabile d'istanza professor
	 */
	private String professor;

	/**
	 * 
	 */
	private Date date;
	private Date expiringDate;
	private String room;
	private String notes;

	/**
	 * Descrizione costruttore
	 * 
	 * @param professor
	 *            descrizione variabile
	 * @param date
	 * @param expiringDate
	 * @param room
	 * @param notes
	 */
	public ExamSession(String professor, Date date, Date expiringDate,
			String room, String notes) {
		super();
		this.professor = professor;
		this.date = date;
		this.expiringDate = expiringDate;
		this.room = room;
		this.notes = notes;
	}

	/**
	 * Inserire descrizione metodo
	 * 
	 * @return inserire descrizione variabile di ritorno
	 */
	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
