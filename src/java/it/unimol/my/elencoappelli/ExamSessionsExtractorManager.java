package it.unimol.my.elencoappelli;

/**
 * Inserire descrizione della classe!!!
 * 
 * @author Giuseppe Bianco
 */
public class ExamSessionsExtractorManager {

	private static ExamSessionsExtractorInterface INSTANCE;

	/**
	 * Inserire commento metodo pubblico!!!
	 * 
	 * @return Dettagliare cosa ritorna il metodo pubblico!!!
	 */
	public static ExamSessionsExtractorInterface getExtractor() {
		if (ExamSessionsExtractorManager.INSTANCE == null) {
			ExamSessionsExtractorManager.INSTANCE = new ExamSessionsExtractor();
		}
		return ExamSessionsExtractorManager.INSTANCE;
	}
}
