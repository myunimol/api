package it.unimol.my.exam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.exceptions.UnirestException;

import it.unimol.my.config.ConfigurationManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Inserire descrizione classe
 *
 * @author Matteo Ianno
 */
@WebServlet(name = "GetExamInfo", urlPatterns = { "/getRecordBookExam" })
public class DetailedExamExtractorServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();

		String token = request.getParameter("token");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String examId = request.getParameter("examId");

		String targetUrl = ConfigurationManager.getInstance()
				.getExamDetailUrl();

		// ottengo l'estrattore
		ExtractorInterface extractor = DetailsExamManager
				.getRecordBookExtractor();

		// estraggo le informazioni
		try {
			DetailedExam detailedExam = extractor.getDetails(targetUrl,
					username, password, examId);
			// converto in JSON
			Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
			String json = gson.toJson(detailedExam);
			// stampo il json a video
			out.println(json);
		} catch (UnirestException ex) {
			ex.printStackTrace();
			out.println("{\"result\":\"failure\",\"msg\":\"unirest exception\"}");
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
		processRequest(request, response);
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
