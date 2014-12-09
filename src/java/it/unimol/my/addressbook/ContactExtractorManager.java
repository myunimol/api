package it.unimol.my.addressbook;

/**
 *
 * @author Carlo Branca
 */
public class ContactExtractorManager {

    /**
     * Il riferimento all'istanza dell'estrattore.
     */
    private static ContactExtractorInterface INSTANCE;

    /**
     * Questo metodo serve a richiamare la classe concreta che effettua il
     * parsing della bacheca di un corso.
     *
     * @return restituisce una istanza valida di ContactExtractorInterface
     */
    public static ContactExtractorInterface getContactExtractor() {
        if (ContactExtractorManager.INSTANCE == null) {
            ContactExtractorManager.INSTANCE = new ContactExtractor();
        }
        return ContactExtractorManager.INSTANCE;
    }

}