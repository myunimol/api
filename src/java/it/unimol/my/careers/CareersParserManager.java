package it.unimol.my.careers;

/**
 * Manager dei LoginParser
 * 
 * @author Ivan Di Rienzo
 */
public class CareersParserManager {

	private static CareersParser INSTANCE;

	/**
	 * Classe con solo metodi statici, costruttore privato
	 */
	private CareersParserManager() {
	}

	/**
	 * @return restituisce una istanza valida di LoginParser
	 */
	public static CareersParser getCareersParser() {
		if (CareersParserManager.INSTANCE == null) {
			CareersParserManager.INSTANCE = new UnimolCareersParser();
		}
		return CareersParserManager.INSTANCE;
	}

}
