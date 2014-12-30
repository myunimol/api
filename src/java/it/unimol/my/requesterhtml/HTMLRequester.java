package it.unimol.my.requesterhtml;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.utils.InsecureHttpClientFactory;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HTMLRequester implements HTMLRequesterInterface {

	/**
	 * Questo metodo restituisce il codice HTML di una pagina web sottoforma di
	 * stringa. La richiesta al server web viene effettuata tramite una
	 * richiesta GET del protocollo HTTP.
	 *
	 * @param targetPage
	 *            L'URL della pagina da scansionare.
	 * @param username
	 *            Lo username dell'utente da autenticare.
	 * @param password
	 *            La password dell'utente da autenticare.
	 *
	 * @author Emilio Fabrizio
	 */
	private ConfigurationManager config = ConfigurationManager.getInstance();

	@Override
	public String get(URL targetPage, String username, String password)
			throws UnirestException {
		HttpRequest request = Unirest.get(targetPage.toString());
		this.myUnimolDefaults(request);
		String jSessionId = this.getJsessionId(username, password);
		this.cookie(request, jSessionId);
		this.auth(request, username, password);
		HttpResponse<String> response = request.asString();
		// effettuiamo il logout dal sistema esse3
		this.logout(username, password, jSessionId);
		return response.getBody();
	}

	@Override
	public String get(URL targetPage) throws UnirestException {
		HttpRequest request = Unirest.get(targetPage.toString());
		this.myUnimolDefaults(request);
		HttpResponse<String> response = request.asString();
		return response.getBody();
	}

	@Override
	public String post(URL targetPage, Map<String, Object> parameters,
			String username, String password) throws UnirestException {
		String jsessionId = this.getJsessionId(username, password);
		HttpRequestWithBody request = Unirest.post(targetPage.toString()
				+ jsessionId);
		this.myUnimolDefaults(request);
		this.cookie(request, jsessionId);
		this.auth(request, username, password);
		request.fields(parameters);
		HttpResponse<String> response = request.asString();
		// effettuiamo il logout dal sistema esse3
		this.logout(username, password, jsessionId);
		return response.getBody();
	}

	@Override
	public String post(URL targetPage, Map<String, Object> parameters)
			throws UnirestException {
		HttpRequestWithBody request = Unirest.post(targetPage.toString());
		this.myUnimolDefaults(request);
		request.fields(parameters);
		HttpResponse<String> response = request.asString();
		return response.getBody();
	}

	@Override
	public String get(URL targetPage, Map<String, Object> parameters,
			String username, String password) throws UnirestException {
		String jSessionId = this.getJsessionId(username, password);
		HttpRequest request = Unirest.get(targetPage.toString());
		this.myUnimolDefaults(request);
		this.cookie(request, jSessionId);
		this.auth(request, username, password);
		request.queryString(parameters);
		HttpResponse<String> response = request.asString();
		// effettuiamo il logout dal sistema esse3
		this.logout(username, password, jSessionId);
		return response.getBody();
	}

	@Override
	public String get(URL targetPage, Map<String, Object> parameters)
			throws UnirestException {
		HttpRequest request = Unirest.get(targetPage.toString());
		this.myUnimolDefaults(request);
		request.queryString(parameters);
		HttpResponse<String> response = request.asString();
		return response.getBody();
	}

	private HttpRequest myUnimolDefaults(HttpRequest request) {
		return request.header("user-agent",
				"MyUnimol fucking user-agent: developed in 2014");
	}

	private HttpRequest cookie(HttpRequest request, String jSessionId) {
		return request.header("cookie", "testCookieEnabled=; JSESSIONID="
				+ jSessionId.replaceFirst(";jsessionid=", ""));
	}

	private HttpRequest auth(HttpRequest request, String username,
			String password) {
		return request.basicAuth(username, password);
	}

	private String getJsessionId(String username, String password) {
		String jsessionId = this.refreshJsessionId(username, password);
		if (!jsessionId.equalsIgnoreCase("")) {
			jsessionId = ";" + jsessionId;
		}
		return jsessionId;
	}

	/**
	 * Questo metodo effettua il logout della sessione.
	 *
	 * @param username
	 *            Lo username dell'utente da autenticare.
	 * @param password
	 *            La password dell'utente da autenticare.
	 * @param jsessionId
	 *
	 * @return conferma del logout, altrimenti errore 500.
	 */
	private int logout(String username, String password, String jsessionId) {
		try {
			Unirest.setHttpClient(InsecureHttpClientFactory.getInsecureClient());
			HttpResponse<String> logout = Unirest.get(
					config.getLogoutUrl() + ";" + jsessionId).asString();
			return logout.getStatus();
		} catch (UnirestException e) {
			e.printStackTrace();
			return 500;
		}
	}

	/**
	 * Questo metodo aggiorna la sessione inviando Username e Password. Una
	 * volta autenticati, salva le credenziali nei cookie.
	 *
	 * @param username
	 *            Lo username dell'utente da autenticare.
	 * @param password
	 *            La password dell'utente da autenticare.
	 * @param jsessionId
	 *
	 * @return cookie con jsessionId
	 */
	private String refreshJsessionId(String username, String password) {
		try {
			Unirest.setHttpClient(InsecureHttpClientFactory.getInsecureClient());
			HttpResponse<String> response = Unirest.get(
					config.getLogonUrl() + "?cod_lingua=ita").asString();
			Headers headers = response.getHeaders();
			List<String> cookies = headers.get("set-cookie");
			if (cookies == null) {
				return "";
			}
			String cookie = cookies.get(0);
			cookie = cookie.replaceFirst("; Path=/; Secure", "");
			return cookie.replaceFirst("JSESSIONID", "jsessionid");
		} catch (UnirestException e) {
			e.printStackTrace();
			return "";
		}
	}
}
