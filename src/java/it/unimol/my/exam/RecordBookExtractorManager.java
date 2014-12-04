package it.unimol.my.exam;

/**
 * Manager degli estrattori del libretto. Fornusce un unico metodo statico per
 * poter richiamare la classe addetta al parsing.
 * 
 * @author Christian De Rita
 */
public class RecordBookExtractorManager {

	/**
	 * Il riferimento all'istanza dell'estrattore.
	 */
	private static RecordBookExtractorInterface INSTANCE;

	/**
	 * Questo metodo serve a richiamare la classe concreta che effettua il
	 * parsing del libretto
	 * 
	 * @return restituisce una istanza valida di RecordBookExtractorInterface
	 */
	public static RecordBookExtractorInterface getRecordBookExtractor() {
		if (RecordBookExtractorManager.INSTANCE == null) {
			RecordBookExtractorManager.INSTANCE = new RecordBookExtractor();
		}
		return RecordBookExtractorManager.INSTANCE;
	}
}
