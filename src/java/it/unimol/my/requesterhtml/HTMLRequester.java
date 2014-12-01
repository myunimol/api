package it.unimol.my.requesterhtml;

import it.unimol.my.utils.Utils;

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

	private String refreshJsessionId(String username, String password) {
		try {
			HttpResponse<String> response = Unirest.get(
					Utils.AUTH_URL_STRING + "?cod_lingua=ita").asString();
			Headers headers = response.getHeaders();

			System.out.println(response.getHeaders());
			System.out.println(response.getBody());

			List<String> cookies = headers.get("set-cookie");
			if (cookies == null)
				return "";
			String cookie = cookies.get(0);
			cookie = cookie.replaceFirst("; Path=/; Secure", "");
			return cookie.replaceFirst("JSESSIONID", "jsessionid");
		} catch (UnirestException e) {
			e.printStackTrace();
			return "";
		}
	}

	private int logout(String username, String password, String jsessionId) {
		try {
			HttpResponse<String> logout = Unirest.get(
					Utils.LOGOUT_URL_STRING + ";" + jsessionId).asString();
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
		HttpRequest request = Unirest
				.get(targetPage.toString() + jsessionId)
				.header("user-agent",
						"MyUnimol fucking user-agent: developed in 2014")
				.header("cookie",
						"testCookieEnabled=; JSESSIONID="
								+ jsessionId.replaceFirst("jsessionid=", ""))
				.basicAuth(username, password);

		HttpResponse<String> response = request.asString();

		// effettuiamo il logout dal sistema esse3
		int logoutCode = this.logout(username, password, jsessionId);

		// ritorniamo il codice di markup html in un oggetto String
		return response.getBody();
	}

	@Override
	public String get(URL targetPage) throws UnirestException {
		HttpResponse<String> response = Unirest.get(targetPage.toString())
				.asString();
		return response.getBody();
	}

	@Override
	public String post(URL targetPage, Map<String, String> parameters,
			String username, String password) throws UnirestException {
		String jsessionId = this.refreshJsessionId(username, password);
		if (!jsessionId.equalsIgnoreCase("")) {
			jsessionId = ";" + jsessionId;
		}

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

	private void fillRequestWithParams(HttpRequestWithBody request,
			Map<String, String> parameters) {
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			request.field(entry.getKey(), entry.getValue());
		}
	}

}
