package it.unimol.my.addressbook;

import java.util.List;

/**
 * Questa è l'interfaccia dell'estrattore di contatti.
 *
 * @author Carlo Branca
 */
public interface ContactExtractorInterface {

    /**
     * Il metodo getContact estrae un contatto da web
     *
     * @param name Il nome desiderato
     * @param surname Il cognome desiderato
     * @return Una lista di contatti che soddisfa i parametri di input
     */
    public List<Contact> getContact(String name, String surname);

}
