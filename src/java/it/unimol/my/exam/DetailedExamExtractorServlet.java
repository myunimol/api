package it.unimol.my.exam;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unimol.my.utils.WebServiceServlet#serve(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void serve(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// recupero examId dalla richiesta
		String examId = request.getParameter("id");
		if (examId == null) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			String msg = config.getMessage("badParameters");
			writer.write("{\"result\":\"failure\",\"msg\":\"" + msg + "\"}");
			return;
		}
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
		}
	}
}
