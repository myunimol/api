package it.unimol.my.requesterhtml;

import it.unimol.my.config.ConfigurationManager;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
			HttpResponse<String> logout = Unirest.get(
					config.getLogoutUrl() + ";" + jsessionId).asString();
			return logout.getCode();
		} catch (UnirestException e) {
			e.printStackTrace();
			return 500;
		}
	}

	@Override
	public String get(URL targetPage, String username, String password)
			throws UnirestException {
		String jsessionId = this.refreshJsessionId(username, password);
		if (!jsessionId.equalsIgnoreCase("")) {
			jsessionId = ";" + jsessionId;
		}
		HttpRequest request = this.createGetRequest(targetPage.toString(),
				username, password, jsessionId);

		HttpResponse<String> response = request.asString();

		// effettuiamo il logout dal sistema esse3
		int logoutCode = this.logout(username, password, jsessionId);

		// ritorniamo il codice di markup html in un oggetto String
		return response.getBody();
	}

	@Override
	public String get(URL targetPage) throws UnirestException {
		HttpResponse<String> response = this.createGetRequest(
				targetPage.toString()).asString();
		return response.getBody();
	}

	@Override
	public String post(URL targetPage, Map<String, String> parameters,
			String username, String password) throws UnirestException {
		String jsessionId = this.getJsessionId(username, password);

		HttpRequestWithBody request = Unirest.post(targetPage.toString()
				+ jsessionId);
		this.fillRequestWithParams(request, parameters);
		HttpResponse<String> response = request.asString();
		// effettuiamo il logout dal sistema esse3
		int logoutCode = this.logout(username, password, jsessionId);
		return response.getBody();
	}

	@Override
	public String post(URL targetPage, Map<String, String> parameters)
			throws UnirestException {
		HttpRequestWithBody request = Unirest.post(targetPage.toString());
		this.fillRequestWithParams(request, parameters);
		HttpResponse<String> response = request.asString();
		return response.getBody();
	}

	/**
	 * Questo metodo riempie la richiesta effettuata in post con tutti i
	 * parametri che sono nella mappa che viene passata come parametro formale.
	 *
	 * @param request
	 * @param parameters
	 */
	private void fillRequestWithParams(HttpRequestWithBody request,
			Map<String, String> parameters) {
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			request.field(entry.getKey(), entry.getValue());
		}
	}

	private String getJsessionId(String username, String password) {
		String jsessionId = this.refreshJsessionId(username, password);
		if (!jsessionId.equalsIgnoreCase("")) {
			jsessionId = ";" + jsessionId;
		}
		return jsessionId;
	}

	private void buildQueryString(HttpRequest request,
			Map<String, String> parameters) {
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			request.queryString(entry.getKey(), entry.getValue());
		}
	}

	private HttpRequest createGetRequest(String urlTarget,
			Map<String, String> parameters, String username, String password,
			String jsessionId) {
		HttpRequest request = this.createGetRequest(urlTarget, username,
				password, jsessionId);
		this.buildQueryString(request, parameters);
		return request;
	}

	private HttpRequest createGetRequest(String urlTarget,
			Map<String, String> parameters) {
		HttpRequest request = this.createGetRequest(urlTarget);
		this.buildQueryString(request, parameters);
		return request;
	}

	private HttpRequest createGetRequest(String urlTarget, String username,
			String password, String jsessionId) {
		HttpRequest request = this.createGetRequest(urlTarget + jsessionId);
		request.header(
				"cookie",
				"testCookieEnabled=; JSESSIONID="
						+ jsessionId.replaceFirst("jsessionid=", ""));
		request.basicAuth(username, password);
		return request;
	}

	private HttpRequest createGetRequest(String urlTarget) {
		HttpRequest request = Unirest.get(urlTarget);
		request.header("user-agent",
				"MyUnimol fucking user-agent: developed in 2014");
		return request;
	}

	@Override
	public String get(URL targetPage, Map<String, String> parameters,
			String username, String password) throws UnirestException {
		String jsessionId = this.getJsessionId(username, password);
		HttpRequest request = this.createGetRequest(targetPage.toString(),
				parameters, username, password, jsessionId);
		HttpResponse<String> response = request.asString();
		return response.getBody();
	}

	@Override
	public String get(URL targetPage, Map<String, String> parameters)
			throws UnirestException {
		HttpRequest request = this.createGetRequest(targetPage.toString(),
				parameters);
		HttpResponse<String> response = request.asString();
		return response.getBody();
	}

}
