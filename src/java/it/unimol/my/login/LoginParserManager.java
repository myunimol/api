package it.unimol.my.login;

/**
 * Manager dei LoginParser
 * 
 * @author Ivan Di Rienzo
 */
public class LoginParserManager {

	private static LoginParser INSTANCE;

	/**
	 * Classe con solo metodi statici, costruttore privato
	 */
	private LoginParserManager() {
	}

	/**
	 * @return restituisce una istanza valida di LoginParser
	 */
	public static LoginParser getLoginParser() {
		if (LoginParserManager.INSTANCE == null) {
			LoginParserManager.INSTANCE = new UnimolLoginParser();
		}
		return LoginParserManager.INSTANCE;
	}

}
