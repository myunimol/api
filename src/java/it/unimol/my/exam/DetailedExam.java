package it.unimol.my.exam;

import java.util.Date;
import java.util.List;

public class DetailedExam extends Exam {

	/**
	 * Descrizione variabile
	 */
	private List<Details> details;

	/**
	 * Descrizione costruttore
	 *
	 * @param name
	 * @param cfu
	 * @param grade
	 * @param date
	 * @param academicYear
	 * @param detailsExam
	 */
	public DetailedExam(String name, int cfu, String grade, Date date,
			String academicYear, String adsceId, List<Details> detailsExam) {
		super(name, cfu, grade, date, academicYear, adsceId);
		this.details = detailsExam;
	}

	/**
	 * Descrizione costruttore
	 */
	public DetailedExam() {
		super();
	}

	/**
	 * @return the detailsExam
	 */
	public List<Details> getDetailsExam() {
		return details;
	}

	/**
	 * @param detailsExam
	 *            the detailsExam to set
	 */
	public void setDetailsExam(List<Details> detailsExam) {
		this.details = detailsExam;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash
				+ (this.details != null ? this.details.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		if (!super.equals(obj))
			return false;

		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DetailedExam other = (DetailedExam) obj;
		if (this.details != other.details
				&& (this.details == null || !this.details
						.equals(other.details))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "DetailedExam{" + "detailsExam="
				+ details + '}';
	}

}
