package it.unimol.my.departmentnews;

/**
 * Manager degli estrattori delle news dei dipartimenti. Fornisce un unico
 * metodo statico per poter richiamare la classe addetta all'estrazione delle
 * news.
 * 
 * @author Giuseppe Bianco
 */
public class NewsExtractorManager {

	private static DepartmentNewsExtractorInterface INSTANCE;

	/**
	 * Questo metodo serve a richiamare la classe concreta che effettua
	 * l'estrazione delle news del dipartimento.
	 * 
	 * @return restituisce una instanza valida di
	 *         DepartmentNewsExtractorInterface.
	 */
	public static DepartmentNewsExtractorInterface getUnimolNewsExtractor() {
		if (INSTANCE == null) {
			INSTANCE = new DepartmentNewsExtractor();
		}
		return INSTANCE;
	}
}
