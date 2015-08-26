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
@WebServlet(name = "NewsBoardServlet", urlPatterns = {"/getNewsBoard"})
public class NewsBoardServlet extends WebServiceServlet {

	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter writer = resp.getWriter();

				
		try {
			// recuperiamo il link alla pagina news desiderata
			String newsPage = req.getParameter("course");
			
			NewsExtractorInterface newsExtractor = NewsBoardBridge.getInstance().getNewsExtractor(newsPage);
			
			List<News> newsList = newsExtractor.getNewsList();
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
			writer.println("{\"result\":\"failure\", \"message\":\"" + e.getMessage() + "\"}");
		}
	}

}
