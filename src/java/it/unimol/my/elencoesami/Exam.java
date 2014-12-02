package it.unimol.my.elencoesami;

import java.util.Date;

public class Exam {

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
	private String grade;
	/**
	 * Descrizione variabile
	 */
	private Date date;
	/**
	 * Descrizione variabile
	 */
	private String academicYear;

	/**
	 * Descrizione costruttore
	 * 
	 * @param name
	 * @param cfu
	 * @param grade
	 * @param date
	 * @param academicYear
	 */
	public Exam(String name, String cfu, String grade, Date date,
			String academicYear) {
		this.name = name;
		this.cfu = cfu;
		this.grade = grade;
		this.date = date;
		this.academicYear = academicYear;
	}

	/**
	 * Descrizione costruttore
	 */
	public Exam() {

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
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
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
	 * @return the academicYear
	 */
	public String getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear
	 *            the academicYear to set
	 */
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

}
