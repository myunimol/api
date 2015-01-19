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
 * La classe AddressBookExtractor contiene i metodi per estrarre la rubrica
 * completa da web o da xml
 *
 * @author Carlo Branca
 */
public class AddressBookExtractor implements AddressBookExtractorInterface {

    @Override
    public List<Contact> getAddressBook() {

        List<Contact> contactList = new ArrayList<Contact>();

        try {
            //mi connetto alla prima pagina per ottenere il numero di contatti totali
            String url = "http://rubrica.unimol.it/ricercaPersone.php?cognome=&nome=&opzione=TUTTI&pagina=1&inviato=SI";
            Document doc = Jsoup.connect(url).get();
            String numRisultati = doc.select("div[class=\"label\"]").text();
            //numRisultati contiene una stringa del tipo "584 risultati"
            String temp = numRisultati.substring(0, 3);
            int risultati = Integer.parseInt(temp);
            int numPagine = risultati / 4;

            for (int i = 1; i <= numPagine; i++) {
                String url2 = "http://rubrica.unimol.it/ricercaPersone.php?cognome=&nome=&opzione=TUTTI&pagina=" + i + "&inviato=SI";
                Document doc2 = Jsoup.connect(url2).get();
                Elements html = doc2.select("div[class=testo]");

                // Estraggo le date delle news
                Elements contacts = html.select("div[class=floatleft_testo_bianco],div[class=floatleft_testo_grigio]");

                for (Element voce : contacts) {
                    if (voce.select("li[class=email]").text().equalsIgnoreCase("prova@unimol.it")) {
                        continue;
                    }
                    Contact contact = new Contact();
                    contact.setFullName(voce.select("p[class=nominativo]").text());
                    contact.setRole(voce.select("p[class=ruolo]").text());
                    contact.setInternalTelephone(voce.select("li[class=interni]").text());
                    contact.setExternalTelephone(voce.select("li[class=esterni]").text());
                    contact.setBuilding(voce.select("li[class=struttura]").text().replace(" (mappa)", ""));
                    contact.setEmail(voce.select("li[class=email]").text());
                    contactList.add(contact);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(AddressBookExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactList;
    }
}
