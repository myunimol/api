package it.unimol.my.exam;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Inserire descrizione classe
 *
 * @author Matteo Ianno
 */
@WebServlet(name = "GetExamInfo", urlPatterns = { "/getRecordBookExam" })
public class DetailedExamExtractorServlet extends Esse3AuthServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = -1125713572833271587L;

	/**
	 * Processa rechieste per <code>POST</code> method.
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
		String examId = request.getParameter("examId");
		// chiedo l'url al gestore configurazioni
		String targetUrl = ConfigurationManager.getInstance()
				.getExamDetailUrl();
		// ottengo una istanza dell'estrattore
		ExtractorInterface extractor = DetailsExamManager
				.getRecordBookExtractor();
		// estraggo le informazioni
		try {
			DetailedExam detailedExam = extractor.getDetails(targetUrl,
					username, password, examId);
			// converto in JSON
			String json = gson.toJson(detailedExam);
			// stampo il json a video
			writer.println(json);
		} catch (UnirestException ex) {
			ex.printStackTrace();
			writer.println("{\"result\":\"failure\",\"msg\":\"unirest exception\"}");
		} finally {
			writer.close();
		}
	}
}
