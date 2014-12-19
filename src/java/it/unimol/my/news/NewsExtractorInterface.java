package it.unimol.my.news;

import java.util.List;

/**
 * Descrizione interfaccia
 *
 * @author Carlo
 */
public interface NewsExtractorInterface {

    /**
     * Descrizione metodo
     *
     * @param url
     * @return
     */
    public List<News> getNewsList(String url);

}
