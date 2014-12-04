package it.unimol.my.exam;

import java.util.List;

/**
 * Questa classe fornisce una astrazione dell'libretto.
 * 
 * Conserva le informazioni relative agli esami,alla media e alla media
 * ponderata
 * 
 * @author Christian De Rita
 */
public class RecordBook {

	/**
	 * Lista degli esami
	 */
	private List<Exam> exams;
	/**
	 * Media dei voti
	 */
	private double average;
	/**
	 * Media ponderata
	 */
	private double weightedAverage;

	/**
	 * @param exams
	 *            Lista degli esami
	 * @param average
	 *            Media
	 * @param weightedAverage
	 *            Media ponderata
	 */
	public RecordBook(List<Exam> exams, double average, double weightedAverage) {
		this.exams = exams;
		this.average = average;
		this.weightedAverage = weightedAverage;
	}

	/**
	 * Costruttore per la creazione di un oggetto RecordBook senza parametri.
	 */
	public RecordBook() {

	}

	/**
	 * Restituisce la lista degli esami
	 * 
	 * @return Lista esami
	 */
	public List<Exam> getExams() {
		return exams;
	}

	/**
	 * Imposta la lista degli esami
	 * 
	 * @param exams
	 *            Esami da settare
	 */
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	/**
	 * Restituisce la media
	 * 
	 * @return la media
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * Imposta la media
	 * 
	 * @param average
	 *            La media da impostare
	 */
	public void setAverage(double average) {
		this.average = average;
	}

	/**
	 * Restituisce la media ponderata
	 * 
	 * @return la media ponderata
	 */
	public double getWeightedAverage() {
		return weightedAverage;
	}

	/**
	 * Imposta la media ponderata
	 * 
	 * @param weightedAverage
	 *            la media ponderata da impostare
	 */
	public void setWeightedAverage(double weightedAverage) {
		this.weightedAverage = weightedAverage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RecordBook [exams=" + exams + ", average=" + average
				+ ", weightedAverage=" + weightedAverage + "]";
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
		long temp;
		temp = Double.doubleToLongBits(average);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((exams == null) ? 0 : exams.hashCode());
		temp = Double.doubleToLongBits(weightedAverage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		RecordBook other = (RecordBook) obj;
		if (Double.doubleToLongBits(average) != Double
				.doubleToLongBits(other.average))
			return false;
		if (exams == null) {
			if (other.exams != null)
				return false;
		} else if (!exams.equals(other.exams))
			return false;
		if (Double.doubleToLongBits(weightedAverage) != Double
				.doubleToLongBits(other.weightedAverage))
			return false;
		return true;
	}

}
