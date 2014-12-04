package it.unimol.my.exam;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.tokenmanagement.TokenManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa servlet da il via al parsing del libretto. Dopo l'elaborazione dei
 * dati da parte delle classi addette al parsing, mostra a video la lista degli
 * appelli disponibili in formato json
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

		ConfigurationManager config = ConfigurationManager.getInstance();
		// controllo token
		TokenManager tokenManager = TokenManager.getInstance();
		if (token == null || token.length() < 16
				|| !tokenManager.tokenExists(token)) {
			String invalidTokenMsg = config.getMessage("invalidToken");
			out.println("{\"result\":\"failure\",\"msg\":\"" + invalidTokenMsg
					+ "\"}");
			return;
		}

		String targetUrl = config.getRecordBookUrl();

		// recupero l'estrattore
		RecordBookExtractorInterface recordBookExtractor = RecordBookExtractorManager
				.getRecordBookExtractor();

		// estraggo il libretto degli esami

		try {
			RecordBook recordBook = recordBookExtractor.getExamsList(targetUrl,
					username, password);
			if (recordBook == null) {
				String unknownErrorMsg = config.getMessage("unknownError");
				out.println("{\"result\":\"failure\",\"msg\":\""
						+ unknownErrorMsg + "\"}");
				return;
			}
			// converto il libretto in json
			Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
			String json = gson.toJson(recordBook);

			// stampo il json a video
			out.println(json);
		} catch (UnirestException e) {
			e.printStackTrace();
			String unirestExceptionMsg = config.getMessage("unirestException");
			out.print("{\"result\":\"failure\", \"msg\":\""
					+ unirestExceptionMsg + "\"}");
			return;
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
		ConfigurationManager config = ConfigurationManager.getInstance();
		String noGetRequestMsg = config.getMessage("noGetRequest");
		PrintWriter out = response.getWriter();
		out.print("{\"result\":\"failure\", \"msg\":\"" + noGetRequestMsg
				+ "\"}");
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
