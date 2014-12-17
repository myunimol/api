package it.unimol.my.unimolnews;

import com.mashape.unirest.http.exceptions.UnirestException;
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

/**
 * Questa classe implementa l'effettivo estrattore delle news dell'Unimol
 * prendendo come input i feed RSS.
 *
 * @author Giuseppe Bianco
 */
public class UnimolNewsExtractor implements UnimolNewsExtractorInterface {

	/**
	 * Questo metodo privato effettua l'estrazione delle news e le rimodella
	 * secondo la struttura di SingleUnimolNews.
	 *
	 * @param targetURL
	 *            stringa relativa all'URL dei feed RSS dell'Unimol.
	 * @return ArrayList di SingleUnimolNews contenente tutte le news estratte e
	 *         rimodellate.
	 */
	private ArrayList<SingleUnimolNews> extractNews(String targetURL) {
		HTMLRequester requester = new HTMLRequester();
		ArrayList<SingleUnimolNews> feedUnimolList = new ArrayList<SingleUnimolNews>();
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
				feedUnimolList.add(new SingleUnimolNews(title, link, content));
			}
		} catch (MalformedURLException ex) {
			Logger.getLogger(UnimolNewsExtractor.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (UnirestException ex) {
			Logger.getLogger(UnimolNewsExtractor.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return feedUnimolList;
	}

	@Override
	public ArrayList<SingleUnimolNews> getUniversityNews() {
		// Ottengo il link dei feed RSS dell'Unimol
		String targetFeedURL = ConfigurationManager.getInstance()
				.getUnimolFeedUrl();
		// Restituisco la lista di news dell'Unimol
		return this.extractNews(targetFeedURL);
	}
}
