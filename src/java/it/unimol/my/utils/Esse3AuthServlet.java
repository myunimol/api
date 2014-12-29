package it.unimol.my.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

public class Esse3AuthServlet extends WebServiceServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = -9172880719702523854L;

	/**
	 * Lo username di esse3
	 */
	protected String username;
	/**
	 * La password di esse3
	 */
	protected String password;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unimol.my.utils.WebServiceServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.setHeaders(resp);
		// ottengo un writer dalla response
		writer = resp.getWriter();
		if (tokenIsValid(req, resp)) {
			if (credentialsAreOk(req, resp)) {
				this.serve(req, resp);
			}
		}
	}

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
		username = req.getParameter("username");
		password = req.getParameter("password");
		// controllo che username e password siano settate
		if (username == null || password == null || username.length() < 1
				|| password.length() < 2) {
			// recupero messaggio d'errore
			String noCredentialsMsg = config.getMessage("noCredentials");
			// imposto lo status code a 401
			resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
			// stampo il messaggio
			writer.print("{\"result\":\"failure\", \"msg\":\""
					+ noCredentialsMsg + "\"}");
			credentialsAreOk = false;
		} else {
			credentialsAreOk = true;
		}
		return credentialsAreOk;
	}

}
