package it.unimol.my.login;

import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;

import javax.servlet.ServletException;
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
@WebServlet(name = "LoginServlet", urlPatterns = { "/test-credentials" })
public class LoginServlet extends Esse3AuthServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = 7946609122833328080L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		// controllo che il token sia valido...
		if (!tokenIsValid(request, response)) {
			// il token non è valido: esco.
			return;
		}
		// controllo che le credenziali siano settate...
		if (!credentialsAreOk(request, response)) {
			// le credenziali non sono settate: esco.
			return;
		}
		// recupero credenziali e examId
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			LoginParser parser = LoginParserManager.getLoginParser();
			UserInformation logInfo = parser.getLoginInformation(username,
					password);
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
		} finally {
			writer.close();
		}
	}
}
