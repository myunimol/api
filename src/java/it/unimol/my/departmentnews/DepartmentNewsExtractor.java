package it.unimol.my.departmentnews;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.my.requesterhtml.HTMLRequester;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

/**
 * Questa classe implementa l'effettivo estrattore delle news dei vari
 * dipartimenti prendendo come input i feed RSS.
 *
 * @author Giuseppe Bianco
 */
public class DepartmentNewsExtractor implements
		DepartmentNewsExtractorInterface {

	/**
	 * Questo metodo privato effettua l'estrazione delle news e le rimodella
	 * secondo la struttura di SingleDepartmentNews.
	 *
	 * @param targetURL
	 *            stringa relativa all'URL dei feed RSS del dipartimento.
	 * @return ArrayList di SingleDepartmentNews contenente tutte le news
	 *         estratte e rimodellate.
	 */
	private ArrayList<SingleDepartmentNews> extractNews(String targetURL) {
		HTMLRequester requester = new HTMLRequester();
		ArrayList<SingleDepartmentNews> feedUnimolList = new ArrayList<SingleDepartmentNews>();
		try {
			String html = requester.get(new URL(targetURL));
			JSONObject xmlJSONObject = XML.toJSONObject(html);
			Object item = xmlJSONObject.getJSONObject("rss")
					.getJSONObject("channel").get("item");
			JSONArray onlyItems = null;
			if(item instanceof JSONObject) {
				onlyItems = new JSONArray();
				onlyItems.put(item);
			} else if (item instanceof JSONArray){
				onlyItems = (JSONArray) item;
			}
			for (int i = 0; i < onlyItems.length(); i++) {
				String title = onlyItems.getJSONObject(i).getString("title");
				String link = onlyItems.getJSONObject(i).getString("link");
				String content = onlyItems.getJSONObject(i).getString(
						"description");
				// String author =
				// onlyItems.getJSONObject(i).getString("author");
				// String publishedDate =
				// onlyItems.getJSONObject(i).getString("pubDate");
				feedUnimolList.add(new SingleDepartmentNews(title, link,
						content));
			}
		} catch (MalformedURLException ex) {
			Logger.getLogger(DepartmentNewsExtractor.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (UnirestException ex) {
			Logger.getLogger(DepartmentNewsExtractor.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return feedUnimolList;
	}

	@Override
	public ArrayList<SingleDepartmentNews> getDepartmentNews(
			String departmentFeedURL) {
		// Restituisco la lista di news dell'Unimol
		return this.extractNews(departmentFeedURL);
	}
}
