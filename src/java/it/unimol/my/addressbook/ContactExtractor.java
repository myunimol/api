package it.unimol.my.addressbook;

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
 * La classe ContactExtractor contiene i metodi per estrarre un contatto da web
 * o da xml
 *
 * @author Carlo Branca
 */
public class ContactExtractor implements ContactExtractorInterface {

    @Override
    public List<Contact> getContact(String name, String surname) {

        List<Contact> contactList = new ArrayList<Contact>();

        try {
            String url = "http://rubrica.unimol.it/ricercaPersone.php?cognome=" + surname + "&nome=" + name + "&opzione=TUTTI&pagina=1&inviato=SI";
            Document doc = Jsoup.connect(url).get();
            Elements html = doc.select("div[class=testo]");

            // Estraggo le date delle news
            Elements contacts = html.select("div[class=floatleft_testo_bianco],div[class=floatleft_testo_grigio]");

            for (Element voce : contacts) {
                Contact contact = new Contact();
                contact.setFullName(voce.select("p[class=nominativo]").text());
                contact.setRole(voce.select("p[class=ruolo]").text());
                contact.setInternalTelephone(voce.select("li[class=interni]").text());
                contact.setExternalTelephone(voce.select("li[class=esterni]").text());
                contact.setBuilding(voce.select("li[class=struttura]").text().replace(" (mappa)", ""));
                contact.setEmail(voce.select("li[class=email]").text());
                contactList.add(contact);
            }

        } catch (IOException ex) {
            Logger.getLogger(AddressBookExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactList;
    }


}
