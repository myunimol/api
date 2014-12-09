package it.unimol.my.news;

/**
 * Classe extractor manager dell'estrattore della bacheca
 * 
 * @author Carlo Branca
 */
public class NewsBoardExtractorManager {

    /**
     * Il riferimento all'istanza dell'estrattore.
     */
    private static NewsBoardExtractorInterface INSTANCE;

    /**
     * Questo metodo serve a richiamare la classe concreta che effettua il
     * parsing della bacheca di un corso.
     *
     * @return restituisce una istanza valida di NewsBoardExtractorInterface
     */
    public static NewsBoardExtractorInterface getNewsBoardExtractor() {
        if (NewsBoardExtractorManager.INSTANCE == null) {
            NewsBoardExtractorManager.INSTANCE = new NewsBoardExtractor();
        }
        return NewsBoardExtractorManager.INSTANCE;
    }
}
