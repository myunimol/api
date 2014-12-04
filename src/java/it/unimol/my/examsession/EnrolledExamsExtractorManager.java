package it.unimol.my.examsession;

/**
 * Manager degli estrattori degli appelli d'esame prenotati. Fornisce un unico
 * metodo statico per poter richiamare la classe addetta al parsing.
 * 
 */
public class EnrolledExamsExtractorManager {

	private static EnrolledExamsExtractorInterface INSTANCE;

	/**
	 * Questo metodo serve a richiamare la classe concreta che effettua il
	 * parsing dell'elenco appelli prenotati
	 * 
	 * @return restituisce una istanza valida di ExamSessionsExtractorInterface
	 */
	public static EnrolledExamsExtractorInterface getExtractor() {
		if (EnrolledExamsExtractorManager.INSTANCE == null) {
			EnrolledExamsExtractorManager.INSTANCE = new EnrolledExamsExtractor();
		}
		return EnrolledExamsExtractorManager.INSTANCE;
	}
}
