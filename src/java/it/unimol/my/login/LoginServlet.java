package it.unimol.my.login;

import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * La servlet che gestisce le richieste di login restituisce le informazioni di
 * base dell'utente sottoforma di JSON
 *
 * @author Ivan Di Rienzo
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/testCredentials" })
public class LoginServlet extends Esse3AuthServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = 7946609122833328080L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unimol.my.utils.WebServiceServlet#serve(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void serve(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		
		try {
			LoginParser parser = LoginParserManager.getLoginParser();
			UserInformation logInfo = parser.getLoginInformation(username,
					password, careerId);
			if (logInfo == null) {
				// login non riuscito
				String badLoginMsg = config.getMessage("badLogin");
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				writer.print("{\"result\":\"failure\", \"msg\":\""
						+ badLoginMsg + "\"}");
			} else {
				writer.print(gson.toJson(logInfo));
			}

		} catch (UnirestException e) {
			String unirestExceptionMsg = config.getMessage("unirestException");
			writer.print("{\"result\":\"failure\", \"msg\":\""
					+ unirestExceptionMsg + "\"}");
		}
	}

}
