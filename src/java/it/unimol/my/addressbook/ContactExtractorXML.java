package it.unimol.my.addressbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Carlo
 */
public class ContactExtractorXML {

    /**
     * Il metodo getContact estrae un contatto da xml
     *
     * @param nome Il nome desiderato
     * @param cognome Il cognome desiderato
     * @return Una lista di contatti che soddisfa i parametri di input
     */
    public List<Contact> getContact(String name, String surname) {

        List<Contact> contactList = new ArrayList<Contact>();

        try {
            File file = new File(ContactExtractorXML.class.getResource("addressBook.xml").getPath());            
            Document doc = Jsoup.parse(file, "UTF-8");
            Elements contatti = doc.select("contact");

            for (Element voce : contatti) {
                if (voce.select("fullname").text().contains(name)
                        && voce.select("fullname").text().contains(surname)) {
                    Contact contact = new Contact();
                    contact.setFullName(voce.select("fullname").text());
                    contact.setRole(voce.select("role").text());
                    contact.setInternalTelephone(voce.select("internalTelephone").text());
                    contact.setExternalTelephone(voce.select("externalTelephone").text());
                    contact.setBuilding(voce.select("building").text());
                    contact.setEmail(voce.select("email").text());
                    contactList.add(contact);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(AddressBookExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactList;
    }
}
