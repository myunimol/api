package it.unimol.my.unimolnews;

import it.unimol.my.utils.WebServiceServlet;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Giuseppe
 */
@WebServlet(name = "UniNewsServlet", urlPatterns = { "/uni-news-servlet" })
public class UnimolNewsServlet extends WebServiceServlet {

	/**
	 * Lo uid seriale della versione
	 */
	private static final long serialVersionUID = 895869795733117798L;

	/**
	 *
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			// Recupero l'estrattore di news dell'Unimol
			UnimolNewsExtractorInterface unimolNewsExtractor = NewsExtractorManager
					.getUnimolNewsExtractor();
			// Recupero la lista di news dell'Unimol
			ArrayList<SingleUnimolNews> unimolNewsList = unimolNewsExtractor
					.getUniversityNews();
			// Converto l'ArrayList in JSON
			String json = gson.toJson(unimolNewsList);
			writer.println(json);
		} finally {
			writer.close();
		}
	}

}
