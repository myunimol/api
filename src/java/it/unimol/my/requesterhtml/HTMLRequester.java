package it.unimol.my.requesterhtml;

import it.unimol.my.config.ConfigurationManager;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HTMLRequester implements HTMLRequesterInterface {
	private static final int TIMEOUT_MIN = 30; 
	
	private String jsessionid;
	private Date lastAccess;
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
	
	protected HTMLRequester() {
	}

	@Override
	public String get(URL targetPage, String username, String password)
			throws UnirestException {
		HttpRequest request = Unirest.get(targetPage.toString());
		this.myUnimolDefaults(request);
		this.cookie(request, this.jsessionid);
		this.auth(request, username, password);
		HttpResponse<String> response = request.asString();
		// effettuiamo il logout dal sistema esse3
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
		HttpRequestWithBody request = Unirest.post(targetPage.toString() + this.jsessionid);
		this.myUnimolDefaults(request);
		this.cookie(request, this.jsessionid);
		this.auth(request, username, password);
		request.fields(parameters);
		HttpResponse<String> response = request.asString();
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
		HttpRequest request = Unirest.get(targetPage.toString());
		this.myUnimolDefaults(request);
		this.cookie(request, this.jsessionid);
		this.auth(request, username, password);
		request.queryString(parameters);
		HttpResponse<String> response = request.asString();
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
		this.lastAccess = new Date();
		return request.header("user-agent",
				"MyUnimol fucking user-agent: developed in 2014");
	}

	private HttpRequest cookie(HttpRequest request, String jSessionId) {
		return request.header("cookie", "JSESSIONID="
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
	public void logout(String username, String password) {
		try {
			Unirest.get(config.getLogoutUrl() + ";" + this.jsessionid).asString();
		} catch (UnirestException e) {
			e.printStackTrace();
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
//			Unirest.setHttpClient(InsecureHttpClientFactory.getInsecureClient());
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

	@Override
	public boolean connect(String username, String password) {
		try {
			this.jsessionid = this.getJsessionId(username, password);
			HttpRequest request = Unirest.get(config.getLogonUrl());
			this.myUnimolDefaults(request);
			this.cookie(request, this.jsessionid);
			this.auth(request, username, password);
			HttpResponse<String> response = request.asString();
			return (response.getStatus() == 200);
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isTimeout() {
		Date now = new Date();
		
		if (now.getTime() - this.lastAccess.getTime() > TIMEOUT_MIN*60*1000)
			return true;
		else
			return false;
	}
}
