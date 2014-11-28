package it.unimol.my.login;

import java.net.URL;

/**
 * Classe che fa il parser per il login di esse3 Unimol
 * 
 * @author Ivan Di Rienzo
 */
public class UnimolLoginParser implements LoginParser {

	@Override
	public UserInformation getLoginInformation(URL urlLogin, String username,
			String password) {

		// TODO things usando metodi privati

		return null;
	}

	/**
	 * Metodo privato che fa il parsing dell'html e restituisce un
	 * UserInformation
	 * 
	 * @param page
	 *            la pagina html
	 * @return Le informazioni dell'utente
	 */
	private UserInformation parsingUserInfo(String page) {

		// TODO

		return null;
	}

	/**
	 * Metodo privato che controlla se il login ha avuto successo
	 * 
	 * @param page
	 *            la pagina html
	 * @return TRUE se il login ha avuto successo FALSE altrimenti
	 */
	private boolean isLogged(String page) {

		// TODO

		return false;
	}

}
