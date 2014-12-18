package it.unimol.my.unimolnews;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.requesterhtml.HTMLRequester;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa classe implementa l'effettivo estrattore delle news dell'Unimol
 * prendendo come input i feed RSS.
 *
 * @author Giuseppe Bianco
 */
public class NewsExtractor implements NewsExtractorInterface {

	@Override
	public ArrayList<News> getUniversityNews() {
		// Ottengo il link dei feed RSS dell'Unimol
		String targetFeedURL = ConfigurationManager.getInstance()
				.getUnimolFeedUrl();
		// Restituisco la lista di news dell'Unimol
		return this.extractNews(targetFeedURL);
	}

	@Override
	public ArrayList<News> getDepartmentNews(String departmentFeedURL) {
		// Restituisco la lista di news dell'Unimol
		return this.extractDepartmentNews(departmentFeedURL);
	}

	/**
	 * Questo metodo privato effettua l'estrazione delle news e le rimodella
	 * secondo la struttura di SingleUnimolNews.
	 *
	 * @param targetURL
	 *            stringa relativa all'URL dei feed RSS dell'Unimol.
	 * @return ArrayList di SingleUnimolNews contenente tutte le news estratte e
	 *         rimodellate.
	 */
	private ArrayList<News> extractNews(String targetURL) {
		HTMLRequester requester = new HTMLRequester();
		ArrayList<News> feedUnimolList = new ArrayList<News>();
		try {
			String html = requester.get(new URL(targetURL));
			JSONObject xmlJSONObject = XML.toJSONObject(html);
			JSONArray onlyItems = xmlJSONObject.getJSONObject("rss")
					.getJSONObject("channel").getJSONArray("item");
			for (int i = 0; i < onlyItems.length(); i++) {
				String title = onlyItems.getJSONObject(i).getString("title");
				String link = onlyItems.getJSONObject(i).getString("link");
				String content = onlyItems.getJSONObject(i).getString(
						"description");
				// String author =
				// onlyItems.getJSONObject(i).getString("author");
				// String publishedDate =
				// onlyItems.getJSONObject(i).getString("pubDate");
				feedUnimolList.add(new News(title, link, content));
			}
		} catch (MalformedURLException ex) {
			Logger.getLogger(NewsExtractor.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (UnirestException ex) {
			Logger.getLogger(NewsExtractor.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return feedUnimolList;
	}

	/**
	 * Questo metodo privato effettua l'estrazione delle news e le rimodella
	 * secondo la struttura di SingleDepartmentNews.
	 *
	 * @param targetURL
	 *            stringa relativa all'URL dei feed RSS del dipartimento.
	 * @return ArrayList di SingleDepartmentNews contenente tutte le news
	 *         estratte e rimodellate.
	 */
	private ArrayList<News> extractDepartmentNews(String targetURL) {
		HTMLRequester requester = new HTMLRequester();
		ArrayList<News> feedUnimolList = new ArrayList<News>();
		try {
			String html = requester.get(new URL(targetURL));
			JSONObject xmlJSONObject = XML.toJSONObject(html);
			Object item = xmlJSONObject.getJSONObject("rss")
					.getJSONObject("channel").get("item");
			JSONArray onlyItems = null;
			if (item instanceof JSONObject) {
				onlyItems = new JSONArray();
				onlyItems.put(item);
			} else if (item instanceof JSONArray) {
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
				feedUnimolList.add(new News(title, link, content));
			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (UnirestException ex) {
			ex.printStackTrace();
		}
		return feedUnimolList;
	}
}
