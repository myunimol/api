package it.unimol.my.login;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet che gestisce le richieste di login restituisce le informazioni di
 * base dell'utente sottoforma di JSON
 *
 * @author Ivan Di Rienzo
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/test-credentials" })
public class LoginServlet extends HttpServlet {

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
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String token = request.getParameter("token");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();
		try {

			if (token == null || username == null || password == null) {
				// aggiungere controllo token non valido
				out.print("{\"result\":\"failure\", \"msg\":\"dati non validi\"}");
			} else {
				LoginParser parser = LoginParserManager.getLoginParser();
				UserInformation logInfo = parser.getLoginInformation(username,
						password);
				if (logInfo == null) {
					// login non riuscito
					out.print("{\"result\":\"failure\", \"msg\":\"login non riuscito\"}");
				} else {
					Gson gson = new Gson();
					// generazione JSON
					out.print(gson.toJson(logInfo));
				}
			}

		} catch (UnirestException e) {
			out.print("{\"result\":\"failure\", \"msg\":\"errore unirest\"}");
		} finally {
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
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
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * Handles the HTTP <code>POST</code> method.
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
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
