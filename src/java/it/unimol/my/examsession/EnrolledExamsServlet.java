package it.unimol.my.examsession;

import it.unimol.my.config.ConfigurationManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * 
 */
@WebServlet(name = "EnrolledExamsServlet", urlPatterns = { "/getEnrolledExams" })
public class EnrolledExamsServlet extends HttpServlet {

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

		String targetURL = ConfigurationManager.getInstance()
				.getEnrolledExamSessionsUrl();

		// commentare per testare online
		// decommentare per testare in locale
		targetURL = "http://localhost:8080/myunimol-webservices/pagine-target/appelliprenotati.html";

		// recupero l'estrattore
		EnrolledExamsExtractorInterface extractor = EnrolledExamsExtractorManager
				.getExtractor();
		// Richiamo l'estrattore del manager e la funzione che effettua il
		// parsing della pagina/file
		// Il risultato è la lista di tutti gli appelli disponibili
		List<EnrolledExamSession> examSessions;
		try {
			examSessions = extractor.getEnrolledExamSessions(targetURL,
					username, password);
			// conversione della "List" di ExamSession in json e stampa a video
			Gson gson = new GsonBuilder()
			   .setDateFormat("dd/MM/yyyy HH:mm").create();
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
