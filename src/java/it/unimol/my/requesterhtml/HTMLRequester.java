package it.unimol.my.requesterhtml;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.utils.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HTMLRequester implements HTMLRequesterInterface {
	private static final int TIMEOUT_MIN = 30; 
	
	private String jsessionid;
	private Date lastAccess;
	private String userAgentHash;
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
		int rand = new Random().nextInt();
		
		try {
			this.userAgentHash = StringUtils.md5(String.valueOf(rand));
		} catch (NoSuchAlgorithmException e) {
			this.userAgentHash = String.valueOf(rand);
		}
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
		HttpRequestWithBody request = Unirest.post(targetPage.toString());
		this.myUnimolDefaults(request);
		this.cookie(request, this.jsessionid);
		this.auth(request, username, password);
		request.fields(parameters);
		HttpResponse<String> response = request.asString();
		if (response.getStatus() == 302) {
			try {
				return this.get(new URL(response.getHeaders().get("location").get(0)), username, password);
			} catch (MalformedURLException e) {
				throw new UnirestException("Unable to parse redirect URL");
			}
		}
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

		request.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		//request.header("Connection", "keep-alive");
		request.header("Host", "unimol.esse3.cineca.it");
		request.header("Accept-Language", "it-IT,it;q=0.8,en-US;q=0.6,en;q=0.4");
		
		return request.header("user-agent",
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:" + this.userAgentHash + ") Gecko/20100101 Firefox/34.0");
	}

	private HttpRequest cookie(HttpRequest request, String jSessionId) {
		return request.header("cookie", "JSESSIONID="
				+ jSessionId.replaceFirst("jsessionid=", ""));
	}

	private HttpRequest auth(HttpRequest request, String username,
			String password) {
		return request.basicAuth(username, password);
	}

	private String getJsessionId(String username, String password) {
		String jsessionId = this.refreshJsessionId(username, password);
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
			Unirest.setHttpClient(HttpClientBuilder.create().build());
			GetRequest request;
			HttpResponse<String> response;
			
			//request = Unirest.get("https://unimol.esse3.cineca.it/");
			//this.myUnimolDefaults(request);
			//response = request.asString();
			
			request = Unirest.get(config.getHomeUrl());
			this.myUnimolDefaults(request);
			response = request.asString();
			
			Headers headers = response.getHeaders();
			List<String> cookies = headers.get("set-cookie");
			if (cookies == null) {
				System.out.println("No JSESSIONID!");
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
			HttpRequest request;
			HttpResponse<String> response;
			
			this.jsessionid = this.getJsessionId(username, password);
			
			request = Unirest.get(config.getLogonUrl() + ";" + this.jsessionid);
			this.myUnimolDefaults(request);
			this.cookie(request, this.jsessionid);
			request.header("Referer", config.getLogonUrl());
			response = request.asString();
			
			request = Unirest.get(config.getLogonUrl() + ";" + this.jsessionid);
			this.myUnimolDefaults(request);
			this.cookie(request, this.jsessionid);
			this.auth(request, username, password);
			request.header("Referer", config.getLogonUrl());
			response = request.asString();
			
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
