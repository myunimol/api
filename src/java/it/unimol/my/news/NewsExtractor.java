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

			for (Element postDiv : news) {
				if (postDiv.children().size() <= 0) {
					continue;
				}
				Element descDiv = postDiv.select("div[class=desc]").first();

				String titleString = "Notizia senza titolo";
				String linkString = url;
				if (descDiv.children().size() > 0) {
					Element title = descDiv.child(0);
					if (!title.text().equals("")) {
						titleString = title.text();
					}
					Element a = descDiv.select("a").first();
					if (a != null) {
						linkString = a.attr("href");
					}
				}

				String textString = "Notizia senza testo";
				if (descDiv.children().size() > 1) {
					Element text = descDiv.child(1);
					if (!text.text().equals("")) {
						textString = text.text();
					}
				}

				String dateString = "Data non disponibile";
				try {
					Element date = postDiv.select("div[class=date]").first();
					if (date != null) {
						dateString = date.text();
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
				News singleNews = new News(dateString, titleString, textString,
						linkString);
				newsList.add(singleNews);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newsList;
	}

}
