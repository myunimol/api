package it.unimol.my.unimolnews;

import it.unimol.my.utils.WebServiceServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Giuseppe
 */
@WebServlet(name = "DepartmentNewsServlet", urlPatterns = { "/getDepartmentNews" })
public class DepartmentNewsServlet extends WebServiceServlet {

	/**
	 * Lo uid seriale della versione.
	 */
	private static final long serialVersionUID = -6564307270618228002L;

	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			// Recupero l'estrattore di news dei dipartimenti
			NewsExtractorInterface departmentNewsExtractor = NewsExtractorManager
					.getNewsExtractor();

			// Recupero il link del dipartimento scelto
			String departmentFeedURL = "";
			// Recupero la scelta dal form
			String department = req.getParameter("department");

			if (department.equals("bioscienzeTerritorio")) {
				departmentFeedURL = config.getBioscienzeTerritorioFeedLink();
			} else {
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
				String msg = config.getMessage("badParameters");
				writer.println("{\"result\":\"failure\", \"msg\":\"" + msg
						+ "\"}");
				return;
			}

			// Recupero la lista di news dell'Unimol
			ArrayList<News> departmentNewsList = departmentNewsExtractor
					.getDepartmentNews(departmentFeedURL);
			// Converto l'ArrayList in JSON
			String json = gson.toJson(departmentNewsList);
			writer.println("{\"newsRss\":" + json + "}");
		} catch (UnirestException ex) {
			ex.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
