package it.unimol.my.elencoesami;

/**
 * Inserire descrizione classe
 * 
 * @author Christian
 */
public class RecordBookExtractorManager {

	/**
	 * Descrizione variabile
	 */
	private static RecordBookExtractorInterface INSTANCE;

	/**
	 * Descrizione metodo statico
	 * @return Una istanza dell'estrattore del libretto degli esami
	 */
	public static RecordBookExtractorInterface getRecordBookExtractor() {
		if (RecordBookExtractorManager.INSTANCE == null) {
			RecordBookExtractorManager.INSTANCE = new RecordBookExtractor();
		}
		return RecordBookExtractorManager.INSTANCE;
	}
}
