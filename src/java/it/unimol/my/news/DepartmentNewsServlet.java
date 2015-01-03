package it.unimol.my.news;

import it.unimol.my.utils.WebServiceServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet per l'estrazione delle news
 *
 * @author Carlo Branca
 */
@WebServlet(name = "DepartmentNewsServlet", urlPatterns = {"/getDepartmentNews"})
public class DepartmentNewsServlet extends WebServiceServlet {

	@Override
	protected void serve(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// recupero l'estrattore
		NewsExtractorInterface newsExtractor = NewsExtractorManager
				.getNewsExtractor();
		// estraggo il libretto degli esami
		try {
			// recuperiamo il link alla pagina news desiderata
			String newsPage = req.getParameter("department");
			String targetUrl;
			switch (newsPage) {
			case "agricolturaAmbienteAlimenti":
				targetUrl = config.getAgricolturaNewsUrl();
				break;
			case "bioscienzeTerritorio":
				targetUrl = config.getBioscienzeTerritorioNewsUrl();
				break;
			case "economiaGestioneSocietaIstituzioni":
				targetUrl = config.getEconomiaNewsUrl();
				break;
			case "giuridico":
				targetUrl = config.getGiuridicoNewsUrl();
				break;
			case "medicinaScienzeSalute":
				targetUrl = config.getMedicinaNewsUrl();
				break;
			case "scienzeUmanisticheSocialiFormazione":
				targetUrl = config.getUmanisticheNewsUrl();
				break;
			default:
				String badParams = config.getMessage("badParameters");
				writer.println("{\"result\":\"failure\",\"msg\":\"" + badParams
						+ "\"}");
				return;
			}
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
