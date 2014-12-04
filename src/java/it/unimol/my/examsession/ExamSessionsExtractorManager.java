package it.unimol.my.examsession;

/**
 * Manager degli estrattori degli appelli d'esame. Fornusce un unico metodo
 * statico per poter richiamare la classe addetta al parsing.
 * 
 * @author Giuseppe Bianco
 */
public class ExamSessionsExtractorManager {

	private static ExamSessionsExtractorInterface INSTANCE;

	/**
	 * Questo metodo serve a richiamare la classe concreta che effettua il
	 * parsing dell'elenco appelli
	 * 
	 * @return restituisce una istanza valida di ExamSessionsExtractorInterface
	 */
	public static ExamSessionsExtractorInterface getExtractor() {
		if (ExamSessionsExtractorManager.INSTANCE == null) {
			ExamSessionsExtractorManager.INSTANCE = new ExamSessionsExtractor();
		}
		return ExamSessionsExtractorManager.INSTANCE;
	}
}
