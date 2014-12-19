package it.unimol.my.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Classe che estrae le news
 *
 * @author Carlo Branca
 */
public class NewsExtractor implements NewsExtractorInterface {

	@Override
	public List<News> getNewsList(String url) {

		List<News> newsList = new ArrayList<News>();

		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements html = doc.select("div[class=posts_wrapper clearfix]");
			Elements news = html.select("div[id^=post-]");

			for (Element element : news) {
				String title = element.select("div[class=desc]").first().child(0).text();				
				String text = element.select("div[class=desc]").first().child(1).text();
				String link = element.select("div[class=desc]").first().select("a").attr("href");
				String date = element.select("div[class=date]").text();
				News singleNews = new News(date, title, text, link);
				newsList.add(singleNews);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newsList;
	}

}
