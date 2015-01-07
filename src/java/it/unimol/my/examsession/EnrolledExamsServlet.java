package it.unimol.my.examsession;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.utils.Esse3AuthServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * 
 */
@WebServlet(name = "EnrolledExamsServlet", urlPatterns = { "/getEnrolledExams" })
public class EnrolledExamsServlet extends Esse3AuthServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = 8254676086309311121L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unimol.my.utils.WebServiceServlet#serve(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter writer = resp.getWriter();
		
		String targetURL = ConfigurationManager.getInstance()
				.getEnrolledExamSessionsUrl();
		// commentare per testare online
		// decommentare per testare in locale
		// targetURL =
		// "http://localhost:8080/myunimol-webservices/pagine-target/appelliprenotati.html";
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
			String json = gson.toJson(examSessions);
			writer.println("{\"result\":\"success\", \"exams\":" + json + "}");
		} catch (UnirestException e) {
			e.printStackTrace();
			writer.println("{\"result\":\"failure\", \"msg\":\"unirest exception\"}");
		}
	}
}
