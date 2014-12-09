package it.unimol.my.addressbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class AddressBookExtractorXML {
    
    /**
     * Il metodo getAddressBook estrae la rubrica completa da XML
     *
     * @return La lista con tutti i contatti della rubrica
     */
    public List<Contact> getAddressBook() {

        List<Contact> contactList = new ArrayList<Contact>();

        try {
            File file = new File(AddressBookExtractorXML.class.getResource("addressBook.xml").getPath());
            Document doc = Jsoup.parse(file, "UTF-8");
            Elements contacts = doc.select("contact");

            for (Element voce : contacts) {
                Contact contact = new Contact();
                contact.setFullName(voce.select("fullname").text());
                contact.setRole(voce.select("role").text());
                contact.setInternalTelephone(voce.select("internalTelephone").text());
                contact.setExternalTelephone(voce.select("externalTelephone").text());
                contact.setBuilding(voce.select("building").text());
                contact.setEmail(voce.select("email").text());
                contactList.add(contact);
            }

        } catch (IOException ex) {
            Logger.getLogger(AddressBookExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contactList;
    }
    
}
