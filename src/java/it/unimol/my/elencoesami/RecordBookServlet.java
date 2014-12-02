package it.unimol.my.elencoesami;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Inserire descrizione classe
 * 
 * @author Christian De Rita
 */
@WebServlet(name = "ExamsListParser", urlPatterns = { "/getRecordBook" })
public class RecordBookServlet extends HttpServlet {

	/**
	 * L'id seriale della versione.
	 */
	private static final long serialVersionUID = 6704202796026709792L;

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

		String targetUrl = "(http://localhost:8080/MyUnimol/Test.html)";

		// recupero l'estrattore
		RecordBookExtractorInterface recordBookExtractor = RecordBookExtractorManager
				.getRecordBookExtractor();

		// estraggo il libretto degli esami
		RecordBook recordBook = recordBookExtractor.getExamsList(targetUrl,
				username, password);

		// TODO controllo che il libretto non sia null o corrotto

		// converto il libretto in json
		Gson gson = new Gson();
		String json = gson.toJson(recordBook);

		// stampo il json a video
		out.println(json);
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
		// non rispondiamo a richieste GET
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
