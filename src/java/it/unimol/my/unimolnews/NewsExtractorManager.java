package it.unimol.my.unimolnews;

/**
 * Manager degli estrattori delle news dell��Unimol e del Dipartimento. Fornisce
 * un unico metodo statico per poter richiamare la classe addetta all'estrazione
 * delle news.
 * 
 * @author Giuseppe Bianco
 */
public class NewsExtractorManager {

	private static NewsExtractorInterface INSTANCE;

	/**
	 * Questo metodo serve a richiamare la classe concreta che effettua
	 * l'estrazione delle news dell'Unimol.
	 * 
	 * @return restituisce una instanza valida di UnimolNewsExtractorInterface.
	 */
	public static NewsExtractorInterface getNewsExtractor() {
		if (INSTANCE == null) {
			INSTANCE = new NewsExtractor();
		}
		return INSTANCE;
	}
}
