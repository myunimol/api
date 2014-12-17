package it.unimol.my.departmentnews;

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
@WebServlet(name = "DepartmentNewsServlet", urlPatterns = { "/department-news-servlet" })
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
			DepartmentNewsExtractorInterface unimolNewsExtractor = NewsExtractorManager
					.getUnimolNewsExtractor();

			// Recupero il link del dipartimento scelto
			String departmentFeedURL = "";
			// Recupero la scelta dal form
			String department = req.getParameter("department");

			if (department.equals("bioscienzeTerritorio")) {
				departmentFeedURL = config.getBioscienzeTerritorioFeedLink();
			}

			// Recupero la lista di news dell'Unimol
			ArrayList<SingleDepartmentNews> unimolNewsList = unimolNewsExtractor
					.getDepartmentNews(departmentFeedURL);
			// Converto l'ArrayList in JSON
			String json = gson.toJson(unimolNewsList);
			writer.println(json);
		} finally {
			writer.close();
		}
	}

}
