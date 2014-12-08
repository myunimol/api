package it.unimol.my.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

public class Esse3AuthServlet extends WebServiceServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = -9172880719702523854L;

	/**
	 * Metodo che serve a verificare che le credenziali passate tramite la
	 * request siano effettivamente presenti e non nulle. Non testa le
	 * credenziali in esse3.
	 * 
	 * @param req
	 *            La richiesta http
	 * @param resp
	 *            La risposta http
	 * @return Un booleano che indica che le credenziali sono presenti e non
	 *         nulle.
	 * @throws IOException
	 *             Nel caso in cui non si riesce a recuperare il PrintWriter
	 *             dalla response.
	 */
	protected boolean credentialsAreOk(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		boolean credentialsAreOk = false;
		// recupero le credenziali dalla richiesta
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// controllo che username e password siano settate
		if (username == null || password == null || username.length() < 1
				|| password.length() < 2) {
			// recupero messaggio d'errore
			String noCredentialsMsg = config.getMessage("noCredentials");
			// imposto lo status code a 401
			resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
			// stampo il messaggio
			writer.print("{\"result\":\"failure\", \"msg\":\"" + noCredentialsMsg
					+ "\"}");
			credentialsAreOk = false;
		} else {
			credentialsAreOk = true;
		}
		return credentialsAreOk;
	}

}
