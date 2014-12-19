package it.unimol.my.news;

/**
 *
 * @author Carlo Branca
 */
public class NewsExtractorManager {

    /**
     * Il riferimento all'istanza dell'estrattore.
     */
    private static NewsExtractorInterface INSTANCE;

    /**
     * Questo metodo serve a richiamare la classe concreta che effettua il
     * parsing della bacheca di un corso.
     *
     * @return restituisce una istanza valida di NewsBoardExtractorInterface
     */
    public static NewsExtractorInterface getNewsExtractor() {
        if (NewsExtractorManager.INSTANCE == null) {
            NewsExtractorManager.INSTANCE = new NewsExtractor();
        }
        return NewsExtractorManager.INSTANCE;
    }
}
