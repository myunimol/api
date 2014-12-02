package it.unimol.my.elencoesami;

import java.util.List;

public class RecordBook {

	private List<Exam> exams;
	private double average;
	private double weightedAverage;

	/**
	 * @param exams
	 * @param average
	 * @param weightedAverage
	 */
	public RecordBook(List<Exam> exams, double average, double weightedAverage) {
		this.exams = exams;
		this.average = average;
		this.weightedAverage = weightedAverage;
	}

	/**
	 * 
	 */
	public RecordBook() {

	}

	/**
	 * @return the exams
	 */
	public List<Exam> getExams() {
		return exams;
	}

	/**
	 * @param exams
	 *            the exams to set
	 */
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * @param average
	 *            the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}

	/**
	 * @return the weightedAverage
	 */
	public double getWeightedAverage() {
		return weightedAverage;
	}

	/**
	 * @param weightedAverage
	 *            the weightedAverage to set
	 */
	public void setWeightedAverage(double weightedAverage) {
		this.weightedAverage = weightedAverage;
	}

}
