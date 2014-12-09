package it.unimol.my.news;

import java.util.List;

/**
 * Interfaccia dell'estrattore delle bacheche degli avvisi
 *
 * @author Carlo
 */
public interface NewsBoardExtractorInterface {

    /**
     * Il metodo si occupa di estrarre le news dalla bacheca di un corso
     *
     * @param url Url della bacheca di un corso di laurea
     * @return La lista di news della bacheca
     */
    public List<News> getNewsBoard(String url);

}
