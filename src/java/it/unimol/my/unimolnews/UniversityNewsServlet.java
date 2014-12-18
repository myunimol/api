package it.unimol.my.unimolnews;

import it.unimol.my.utils.WebServiceServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Giuseppe
 */
@WebServlet(name = "UniNewsServlet", urlPatterns = { "/getUniversityNews" })
public class UniversityNewsServlet extends WebServiceServlet {

	/**
	 * Lo uid seriale della versione
	 */
	private static final long serialVersionUID = 895869795733117798L;

	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			// Recupero l'estrattore di news dell'Unimol
			NewsExtractorInterface unimolNewsExtractor = NewsExtractorManager
					.getNewsExtractor();
			// Recupero la lista di news dell'Unimol
			ArrayList<News> unimolNewsList = unimolNewsExtractor
					.getUniversityNews();
			// Converto l'ArrayList in JSON
			String json = gson.toJson(unimolNewsList);
			writer.println("{\"newsRss\":" + json + "}");
		} catch (UnirestException ex) {
			ex.printStackTrace();
		} finally {
			writer.close();
		}
	}

}
