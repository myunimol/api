package it.unimol.my.news.extractors;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.news.News;
import it.unimol.my.news.NewsExtractorInterface;
import it.unimol.my.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Classe che estrae le news
 *
 * @author Carlo Branca
 */
public class MedicinaNewsExtractor implements NewsExtractorInterface {

	@Override
	public List<News> getNewsList() {
		String urlLectures = ConfigurationManager.getInstance().getMedicinaNewsBoardUrl1();
		String urlExams = ConfigurationManager.getInstance().getMedicinaNewsBoardUrl2();
		
		List<News> newsList1 = new ArrayList<News>();
		List<News> newsList2 = new ArrayList<News>();

		Document doc;
		try {
			doc = Jsoup.connect(urlLectures).get();
			Elements html = doc.select("div[class=the_content_wrapper]");
			Elements news = html.select("tr");

			boolean header = true;
			for (Element postDiv : news) {
				if (header) {
					header = false;
					continue;
				}
				
				String newsBody = StringUtils.realTrim(postDiv.select("td").get(0).text());
				String newsDate = StringUtils.realTrim(postDiv.select("td").get(1).text());
				
				if (newsBody.length() == 0 && newsDate.length() == 0)
					break;

				String titleString = "Avviso lezioni";
				
				News singleNews = new News(newsDate, titleString, newsBody, urlLectures);
				newsList1.add(singleNews);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			doc = Jsoup.connect(urlExams).get();
			Elements html = doc.select("div[class=the_content_wrapper]");
			Elements news = html.select("p");
			
			for (Element postDiv : news) {
				String newsBody = "";
				String newsDate = "";
				Elements links = postDiv.select("a");
				String link = urlExams;
				if (!links.isEmpty()) {
					link = links.first().attr("href");
					newsBody = links.first().text();
				} else {
					newsBody = postDiv.text();
				}
				
				String titleString = "Avviso esami";
				
				News singleNews = new News(newsDate, titleString, newsBody, link);
				newsList2.add(singleNews);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Collections.reverse(newsList1);
		Collections.reverse(newsList2);
		
		List<News> result = new ArrayList<News>();
		result.addAll(newsList1.subList(0, (newsList1.size() >= 5 ? 4 : newsList1.size())));
		result.addAll(newsList2.subList(0, (newsList2.size() >= 5 ? 4 : newsList2.size())));
		

		return result;
	}

	@Override
	public List<News> getNewsList(String url) {
		throw new NotImplementedException();
	}
}
