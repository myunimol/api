/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.addressbook;

/**
 *
 * @author Carlo Branca
 */
public class AddressBookExtractorManager {

    /**
     * Il riferimento all'istanza dell'estrattore.
     */
    private static AddressBookExtractorInterface INSTANCE;

    /**
     * Questo metodo serve a richiamare la classe concreta che effettua il
     * parsing della bacheca di un corso.
     *
     * @return restituisce una istanza valida di AddressBookExtractorInterface
     */
    public static AddressBookExtractorInterface getAddressBookExtractor() {
        if (AddressBookExtractorManager.INSTANCE == null) {
            AddressBookExtractorManager.INSTANCE = new AddressBookExtractor();
        }
        return AddressBookExtractorManager.INSTANCE;
    }

}
