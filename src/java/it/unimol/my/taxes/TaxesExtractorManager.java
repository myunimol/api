package it.unimol.my.taxes;

/**
 * Manager degli estrattori delle tasse da pagare. Fornisce un unico metodo statico per
 * poter richiamare la classe addetta al parsing.
 * 
 * @author Carlo Branca
 */
public class TaxesExtractorManager {

	/**
	 * Il riferimento all'istanza dell'estrattore.
	 */
	private static TaxesExtractorInterface INSTANCE;

	/**
	 * Questo metodo serve a richiamare la classe concreta che effettua il
	 * parsing delle tasse
	 * 
	 * @return restituisce una istanza valida di RecordBookExtractorInterface
	 */
	public static TaxesExtractorInterface getTaxesExtractor() {
		if (TaxesExtractorManager.INSTANCE == null) {
			TaxesExtractorManager.INSTANCE = new TaxesExtractor();
		}
		return TaxesExtractorManager.INSTANCE;
	}
}
