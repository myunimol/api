package it.unimol.my.requesterhtml;

import java.net.URL;
import java.util.Map;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface HTMLRequesterInterface {

	/**
	 * Questo metodo restituisce il codice di markup HTML di una pagina web
	 * protetta da login tramite la basic authentication di http. La richiesta
	 * al server web viene effettuata tramite una richiesta GET del protocollo
	 * HTTP.
	 *
	 * @param targetPage
	 *            L'URL della pagina da scansionare.
	 * @param username
	 *            Lo username dell'utente da autenticare.
	 * @param password
	 *            La password dell'utente da autenticare.
	 *
	 * @return Il codice di markup HTML sottoforma di String.
	 *
	 * @author Emilio Fabrizio
	 */
	public String get(URL targetPage, String username, String password)
			throws UnirestException;

	public String get(URL targetPage, Map<String, String> parameters,
			String username, String password) throws UnirestException;

	public String get(URL targetPage, Map<String, String> parameters)
			throws UnirestException;

	public String get(URL targetPage) throws UnirestException;

	public String post(URL targetPage, Map<String, String> parameters,
			String username, String password) throws UnirestException;

	public String post(URL targetPage, Map<String, String> parameters)
			throws UnirestException;

}
