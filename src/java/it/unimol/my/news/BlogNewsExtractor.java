package it.unimol.my.news;

import java.util.List;

/**
 * Classe che estrae le news
 *
 * @author Carlo Branca
 */
public abstract class BlogNewsExtractor extends GenericNewsExtractor {
	@Override
	public final List<News> getNewsList(String url) {
		return super.getNewsList(url);
	}
}
