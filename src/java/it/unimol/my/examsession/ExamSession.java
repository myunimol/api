package it.unimol.my.examsession;

import java.util.Date;

public class ExamSession {

	/**
	 * Nome dell'appello
	 */
	private String name;
	/**
	 * Data dell'appello
	 */
	private Date date;
	/**
	 * Aula dell'appello
	 */
	private String room;
	/**
	 * professiore/i responsabili dell'esame
	 */
	private String professor;
	/**
	 * Note dell'appello
	 */
	private String notes;
	/**
	 * CFU dell'esame
	 * 
	 * @deprecated Per questa versione non ci interessiamo ai CFU
	 */
	private String cfu;

	/**
	 * Costruttore per la creazione di un oggetto ExamSession senza parametri.
	 */
	public ExamSession() {
		this.cfu = "0";
	}

	/**
	 * Costruttore completo per la creazione di un oggetto ExamSession. Prende
	 * in imput tutte le informazioni disponibili dalla pagina della lista degli
	 * appelli.
	 * 
	 * @param name
	 *            Il nome dell'esame
	 * @param date
	 *            La data dell'appello
	 * @param room
	 *            L'aula dove si tiene l'esame
	 * @param professor
	 *            I nomi dei professori
	 * @param notes
	 *            Le note dell'esame
	 * @param cfu
	 *            I CFU dell'esame
	 */
	public ExamSession(String name, Date date, String room, String professor,
			String notes, String cfu) {
		super();
		this.name = name;
		this.date = date;
		this.room = room;
		this.professor = professor;
		this.notes = notes;
		this.cfu = cfu;
	}

	/**
	 * Ritorna una stringa relativa alla materia dell'appello.
	 * 
	 * @return la materia dell'appello come stringa
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo per impostare la materia relativa all'appello
	 * 
	 * @param name
	 *            stringa che rappresenta la materia dell'appello
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ritorna un oggetto Date con la data in cui si svolgera' l'esame. Il
	 * formato utilizzato da Esse3 e' GG/MM/AAAA
	 * 
	 * @return la data dell'appello
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Metodo per impostare il giorno di svolgimento dell'esame.
	 * 
	 * @param date
	 *            Oggetto data che rappresenta la data dell'appello.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Ritorna una stringa contenente l'aula dove si svolgerà l'appello
	 * 
	 * @return stringa contenente l'aula dove si svolgerà l'appello
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * Imposta la stringa contenente l'aula dove si svolgerà l'appello
	 * 
	 * @param room
	 *            La stringa contenente l'aula dove si svolgerà l'appello
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * Metodo per ottenere il professore relativo all'appello
	 * 
	 * @return Stringa contenente il professore designato per l'appello
	 */
	public String getProfessor() {
		return professor;
	}

	/**
	 * Metodo per impostare il professore designato per l'appello.
	 * 
	 * @param professor
	 *            stringa contentente il prof. designato per l'appello.
	 */
	public void setProfessor(String professor) {
		this.professor = professor;
	}

	/**
	 * Ritorna il numero di CFU dell'esame
	 * 
	 * @return il numero di CFU dell'esame
	 */
	public String getCfu() {
		return cfu;
	}

	/**
	 * Imposta il numero di CFU per l'esame
	 * 
	 * @param cfu
	 *            numero di CFU per l'esame
	 */
	public void setCfu(String cfu) {
		this.cfu = cfu;
	}

	/**
	 * Imposta le note relative all'appello d'esame
	 * 
	 * @param notes
	 *            Le note per l'appello
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Ritorna le note per l'appello d'esame
	 * 
	 * @return le note per l'appello d'esame
	 */
	public String getNotes() {
		return this.notes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamSession [name=" + name + ", date=" + date + ", room="
				+ room + ", professor=" + professor + ", notes=" + notes
				+ ", cfu=" + cfu + "]";
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
		result = prime * result + ((cfu == null) ? 0 : cfu.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result
				+ ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
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
		ExamSession other = (ExamSession) obj;
		if (cfu == null) {
			if (other.cfu != null)
				return false;
		} else if (!cfu.equals(other.cfu))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

}
