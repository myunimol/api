package it.unimol.my.elencoappelli;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa servlet da il via al parsing ella lista degli appelli d'esame. Dopo
 * l'elaborazione dei dati da parte delle classi addette al parsing, mostra a
 * video la lista degli appelli disponibili in formato json
 * 
 * @author Giuseppe Bianco
 */
@WebServlet(name = "ExamSessionsServlet", urlPatterns = { "/exam-sessions" })
public class ExamSessionsServlet extends HttpServlet {

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

		// TODO integrare componente di validazione del token (quando pronta)

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		PrintWriter printWriter = response.getWriter();

		// Questo sarà l'url relativo alla pagina della lista degli appelli
		// d'esame disponibili
//		String targetURL = "https://unimol.esse3.cineca.it/auth/studente/Appelli/Appelli.do";
		String targetURL = "http://localhost:8080/myunimol-webservices/pagine-target/elencoappelliUNIMOL.html";

		// recupero l'estrattore
		ExamSessionsExtractorInterface extractor = ExamSessionsExtractorManager
				.getExtractor();
		// Richiamo l'estrattore del manager e la funzione che effettua il
		// parsing della pagina/file
		// Il risultato è la lista di tutti gli appelli disponibili
		List<ExamSession> examSessions;
		try {
			examSessions = extractor.getExamSessions(targetURL, username,
					password);
			// conversione della "List" di ExamSession in json e stampa a video
			Gson gson = new Gson();
			String json = gson.toJson(examSessions);
			printWriter.println(json);
		} catch (UnirestException e) {
			e.printStackTrace();
			printWriter
					.println("{\"result\":\"failure\", \"msg\":\"unirest exception\"}");
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
		// le richieste in GET non vengono accettate
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
