package it.unimol.my.taxes;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.my.requesterhtml.HTMLRequester;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Estrattore delle tasse universitarie
 * 
 * @author Carlo Branca
 */
public class TaxesExtractor implements TaxesExtractorInterface {

    @Override
    public List<Tax> getTaxesList(String targetURL,
            String username, String password) throws UnirestException {
        List<Tax> taxes = new ArrayList<Tax>();
        HTMLRequester requester = new HTMLRequester();
        String html = null;
        try {
            html = requester.get(new URL(targetURL), username, password);
            Document doc = Jsoup.parse(html);
            Elements tableRows = doc.select("table[class=detail_table] tr");
            if (tableRows == null || tableRows.isEmpty()) {
                return taxes;
            }
            for (Element element : tableRows.subList(1, tableRows.size())) {
                Elements columns = element.select("td[class=detail_table]");
                if (columns == null || columns.isEmpty()) {
                    return taxes;
                }
                if (element.text().equals("Pagamento non pervenuto")) {
                    //mettere break per non visualizzare le tasse da pagare
                    continue;
                }
                if (element.text().equals("Pagamento effettuato")) {
                    //mettere break per non visualizzare le tasse pagare
                    continue;
                }
                Iterator<Element> ite = columns.iterator();
                while (ite.hasNext()) {
                    String billId = ite.next().text();
                    String bullettinCode = ite.next().text();
                    String year = ite.next().text();
                    String description = ite.next().text();
                    String expiringDate = ite.next().text();
                    String amountString = ite.next().text();
                    amountString = amountString.replace(',', '.');
                    amountString = amountString.replaceAll("[^0-9\\.]", "");
                    Double amountDouble = Double.parseDouble(amountString);
                    Element statusElement = ite.next().child(0);
                    String status = "";
                    if (statusElement.attr("title").contains("non pagato")) {
                        status = "non pagato";
                    } else {
                        status = "pagato";
                    }
                    Tax tax = new Tax(billId, bullettinCode, year, description,
                            expiringDate, amountDouble, status);
                    taxes.add(tax);
                }
            }
            return taxes;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return taxes;
        }
    }

}
