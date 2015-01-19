package it.unimol.my.examsession;

import java.util.Date;

/**
 * 
 *
 */
public class EnrolledExamSession extends ExamSession {

	private String enrollmentPosition;
	private String enrolled;

	/**
	 * 
	 * @param name
	 * @param date
	 * @param room
	 * @param professor
	 * @param notes
	 * @param cfu
	 * @param enrollmentPosition
	 * @param enrolled
	 */
	public EnrolledExamSession(String name, Date date, String room,
			String professor, String notes, String id, String cfu,
			String enrollmentPosition, String enrolled) {
		super(name, date, room, professor, notes, id, cfu);
		this.enrollmentPosition = enrollmentPosition;
		this.enrolled = enrolled;
	}

	/**
	 * 
	 */
	public EnrolledExamSession() {
		super();
	}

	/**
	 * @return the enrollmentPosition
	 */
	public String getEnrollmentPosition() {
		return enrollmentPosition;
	}

	/**
	 * @param enrollmentPosition
	 *            the enrollmentPosition to set
	 */
	public void setEnrollmentPosition(String enrollmentPosition) {
		this.enrollmentPosition = enrollmentPosition;
	}

	/**
	 * @return the enrolled
	 */
	public String getEnrolled() {
		return enrolled;
	}

	/**
	 * @param enrolled
	 *            the enrolled to set
	 */
	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "\nEnrolledExamSession [enrollmentPosition="
				+ enrollmentPosition + ", enrolled=" + enrolled + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((enrolled == null) ? 0 : enrolled.hashCode());
		result = prime
				* result
				+ ((enrollmentPosition == null) ? 0 : enrollmentPosition
						.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnrolledExamSession other = (EnrolledExamSession) obj;
		if (enrolled == null) {
			if (other.enrolled != null)
				return false;
		} else if (!enrolled.equals(other.enrolled))
			return false;
		if (enrollmentPosition == null) {
			if (other.enrollmentPosition != null)
				return false;
		} else if (!enrollmentPosition.equals(other.enrollmentPosition))
			return false;
		return true;
	}

}
