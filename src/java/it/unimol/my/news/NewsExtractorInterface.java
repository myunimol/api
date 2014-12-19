package it.unimol.my.news;

import java.util.List;

/**
 * Interfaccia estrattore delle news
 *
 * @author Carlo Branca
 */
public interface NewsExtractorInterface {

    /**
     * Metodo che estrae le news
     *
     * @param url Url della pagina da estrarre
     * @return lista di news
     */
    public List<News> getNewsList(String url);

}
