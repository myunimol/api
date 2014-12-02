package it.unimol.my.dettaglioappello;

/**
 * Inserire descrizione classe
 * 
 * @author Pasquale Fornaro
 */
public class EnrolledExamSessionsManager {

	/**
	 * L'istanza dell'estrattore delle sessioni d'esame a cui si &egrave;
	 * prenotati.
	 */
	private static EnrolledExamSessionsExtractorInterface INSTANCE;

	/**
	 * Inserire descrizione metodo.
	 * 
	 * @return L'istanza dell'estrattore delle sessioni d'esame a cui si
	 *         &egrave; prenotati.
	 */
	public static EnrolledExamSessionsExtractorInterface getExamSessionsExtractor() {
		if (EnrolledExamSessionsManager.INSTANCE == null) {
			EnrolledExamSessionsManager.INSTANCE = new EnrolledExamSessionsExtractor();
		}
		return EnrolledExamSessionsManager.INSTANCE;
	}
}
