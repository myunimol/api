package it.unimol.my.dettaglioappello;

import java.util.Date;

/**
 * Questa classe fornisce una astrazione sul dato 'sessione d'esame a cui si
 * &egrave; prenotati'
 * 
 * @author Matteo Merola
 *
 */
public class EnrolledExamSession {

	/**
	 * Descrizione variabile
	 */
	private String name;
	/**
	 * Descrizione variabile
	 */
	private String cfu;
	/**
	 * Descrizione variabile
	 */
	private String professor;
	/**
	 * Descrizione variabile
	 */
	private Date date;
	/**
	 * Descrizione variabile
	 */
	private Date expiringDate;
	/**
	 * Descrizione variabile
	 */
	private String room;
	/**
	 * Descrizione variabile
	 */
	private int enrollmentPosition;
	/**
	 * Descrizione variabile
	 */
	private int enrolledStudents;
	/**
	 * Descrizione variabile
	 */
	private String notes;

	/**
	 * Inserire descrizione costruttore
	 * 
	 * @param name
	 * @param cfu
	 * @param professor
	 * @param date
	 * @param expiringDate
	 * @param room
	 * @param enrollmentPosition
	 * @param enrolledStudents
	 * @param notes
	 */
	public EnrolledExamSession(String name, String cfu, String professor,
			Date date, Date expiringDate, String room, int enrollmentPosition,
			int enrolledStudents, String notes) {
		this.name = name;
		this.cfu = cfu;
		this.professor = professor;
		this.date = date;
		this.expiringDate = expiringDate;
		this.room = room;
		this.enrollmentPosition = enrollmentPosition;
		this.enrolledStudents = enrolledStudents;
		this.notes = notes;
	}

	/**
	 * Inserire descrizione costruttore
	 */
	public EnrolledExamSession() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cfu
	 */
	public String getCfu() {
		return cfu;
	}

	/**
	 * @param cfu
	 *            the cfu to set
	 */
	public void setCfu(String cfu) {
		this.cfu = cfu;
	}

	/**
	 * @return the professor
	 */
	public String getProfessor() {
		return professor;
	}

	/**
	 * @param professor
	 *            the professor to set
	 */
	public void setProfessor(String professor) {
		this.professor = professor;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the expiringDate
	 */
	public Date getExpiringDate() {
		return expiringDate;
	}

	/**
	 * @param expiringDate
	 *            the expiringDate to set
	 */
	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @return the enrollmentPosition
	 */
	public int getEnrollmentPosition() {
		return enrollmentPosition;
	}

	/**
	 * @param enrollmentPosition
	 *            the enrollmentPosition to set
	 */
	public void setEnrollmentPosition(int enrollmentPosition) {
		this.enrollmentPosition = enrollmentPosition;
	}

	/**
	 * @return the enrolledStudents
	 */
	public int getEnrolledStudents() {
		return enrolledStudents;
	}

	/**
	 * @param enrolledStudents
	 *            the enrolledStudents to set
	 */
	public void setEnrolledStudents(int enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfu == null) ? 0 : cfu.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + enrolledStudents;
		result = prime * result + enrollmentPosition;
		result = prime * result
				+ ((expiringDate == null) ? 0 : expiringDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result
				+ ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnrolledExamSession other = (EnrolledExamSession) obj;
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
		if (enrolledStudents != other.enrolledStudents)
			return false;
		if (enrollmentPosition != other.enrollmentPosition)
			return false;
		if (expiringDate == null) {
			if (other.expiringDate != null)
				return false;
		} else if (!expiringDate.equals(other.expiringDate))
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

	@Override
	public String toString() {
		return "EnrolledExamSession [name=" + name + ", cfu=" + cfu
				+ ", professor=" + professor + ", date=" + date
				+ ", expiringDate=" + expiringDate + ", room=" + room
				+ ", enrollmentPosition=" + enrollmentPosition
				+ ", enrolledStudents=" + enrolledStudents + ", notes=" + notes
				+ "]";
	}

}
