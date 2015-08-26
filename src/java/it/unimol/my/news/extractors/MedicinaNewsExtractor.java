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
		
		List<News> newsListLectures = new ArrayList<News>();
		List<News> newsListExams = new ArrayList<News>();
		List<News> newsListExamsResults = new ArrayList<News>();

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
				newsListLectures.add(singleNews);
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
				List<News> addToMe = null;
				
				String newsBody = "";
				String newsDate = "";
				Elements links = postDiv.select("a");
				String link = urlExams;
				
				String titleString;
				
				if (!links.isEmpty()) {
					link = links.first().attr("href");
					newsBody = links.first().text();
					titleString = "Avviso risultati esami";
					
					addToMe = newsListExamsResults;
				} else {
					titleString = "Avviso esami";
					newsBody = postDiv.text();
					
					addToMe = newsListExams;
				}
				
				News singleNews = new News(newsDate, titleString, newsBody, link);
				addToMe.add(singleNews);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Collections.reverse(newsListLectures);
		Collections.reverse(newsListExams);
		
		List<News> result = new ArrayList<News>();
		result.addAll(newsListLectures.subList(0, (newsListLectures.size() >= 5 ? 4 : newsListLectures.size())));
		result.addAll(newsListExams.subList(0, (newsListExams.size() >= 5 ? 4 : newsListExams.size())));
		result.addAll(newsListExamsResults.subList(0, (newsListExamsResults.size() >= 5 ? 4 : newsListExamsResults.size())));
		

		return result;
	}

	@Override
	public List<News> getNewsList(String url) {
		throw new NotImplementedException();
	}
}
