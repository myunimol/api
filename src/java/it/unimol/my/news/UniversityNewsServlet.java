package it.unimol.my.news;

import it.unimol.my.utils.WebServiceServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet per l'estrazione delle news
 *
 * @author Carlo Branca
 */
@WebServlet(name = "UniversityNewsServlet", urlPatterns = { "/getUniversityNews" })
public class UniversityNewsServlet extends WebServiceServlet {
	private static NewsExtractorInterface newsExtractor;

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = -8976875665816142425L;

	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter writer = resp.getWriter();

		// recupero l'estrattore
		if (newsExtractor == null)
			newsExtractor = new GenericNewsExtractor();
		
		// estraggo il libretto degli esami
		try {
			// recuperiamo il link alla pagina news desiderata
			String targetUrl = config.getUniversityNewsUrl();

			List<News> newsList = newsExtractor.getNewsList(targetUrl);
			if (newsList == null) {
				String unknownErrorMsg = config.getMessage("unknownError");
				writer.println("{\"result\":\"failure\",\"msg\":\""
						+ unknownErrorMsg + "\"}");
				return;
			}
			// converto il libretto in json
			String json = gson.toJson(newsList);
			// stampo il json a video
			writer.println("{\"newsList\":" + json + "}");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
