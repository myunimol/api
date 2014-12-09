package it.unimol.my.addressbook;

import java.util.List;

/**
 * Questa è l'interfaccia dell'estrattore della rubrica
 *
 * @author Carlo Branca
 */
public interface AddressBookExtractorInterface {

    /**
     * Il metodo getAddressBook estrae la rubrica completa da web
     *
     * @return La lista con tutti i contatti della rubrica
     */
    public List<Contact> getAddressBook();

}
