package it.unimol.my.news.extractors;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.news.BlogNewsExtractor;
import it.unimol.my.news.News;
import it.unimol.my.news.NewsExtractorInterface;

import java.io.IOException;
import java.util.ArrayList;
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
public class ScienzeFormazionePrimariaNewsExtractor extends BlogNewsExtractor {

	@Override
	public List<News> getNewsList() {
		String url = ConfigurationManager.getInstance().getScienzeFormazionePrimariaNewsBoardUrl();

		return this.getNewsList(url);
	}
}
