package it.unimol.my.taxes;

import com.mashape.unirest.http.exceptions.UnirestException;

import it.unimol.my.requesterhtml.HTMLRequesterException;
import it.unimol.my.requesterhtml.HTMLRequesterInterface;
import it.unimol.my.requesterhtml.HTMLRequesterManager;

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
            String username, String password, String pCareerId) throws UnirestException {
        List<Tax> taxes = new ArrayList<Tax>();
        HTMLRequesterInterface requester;
		try {
			requester = HTMLRequesterManager.getManager().getInstance(username, password, pCareerId);
		} catch (HTMLRequesterException e1) {
			throw new UnirestException(e1.getMessage());
		}
		
        String html = null;
        try {
            html = requester.get(new URL(targetURL), username, password);
            Document doc = Jsoup.parse(html);
            Elements tableRows = doc.select("table[class=detail_table] tr");
            if (tableRows == null)
            	throw new UnirestException("Problems with Esse3: Not responding!");
            else if (tableRows.isEmpty())
                return taxes;
            for (Element element : tableRows.subList(1, tableRows.size())) {
                Elements columns = element.select("td[class=detail_table]");
                if (columns == null)
                	throw new UnirestException("Problems with Esse3: Not responding!");
                else if (columns.isEmpty()) {
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
                if(columns.size()==1){
                    
                    continue;
                }
                Iterator<Element> ite = columns.iterator();
                while (ite.hasNext()) {
                    String billId = null;
                    String bullettinCode = null;
                    String year = null;
                    String description = null;
                    String expiringDate = null;
                    String amountString = null;
                    Double amountDouble = null;
                    if(ite.hasNext()) {
                    	Element n = ite.next();
                    	if(n!=null)
                    		billId = n.text();
                    }
                    if(ite.hasNext()) {
                    	Element n = ite.next();
                    	if(n!=null)
                    		bullettinCode = n.text();
                    }
                    if(ite.hasNext()) {
                    	Element n = ite.next();
                    	if(n!=null)
                    		year = n.text();
                    }
                    if(ite.hasNext()) {
                    	Element n = ite.next();
                    	if(n!=null)
                    		description = n.text();
                    }
                    if(ite.hasNext()) {
                    	Element n = ite.next();
                    	if(n!=null)
                    		expiringDate = n.text();
                    }
                    if(ite.hasNext()) {
                    	Element n = ite.next();
                    	if(n!=null) {
                    		amountString = n.text();
	                    	amountString = amountString.replace(',', '.');
	                        amountString = amountString.replaceAll("[^0-9\\.]", "");
	                        amountDouble = Double.parseDouble(amountString);
                    	}
                    }
                    
                    Element statusElement = null;
                    if(ite.hasNext()) {
                    	Element child = ite.next();
                    	statusElement = child.child(0);
                    }
                    String status;
                    if (statusElement!=null && statusElement.attr("title") != null && statusElement.attr("title").contains("non pagato")) {
                        status = "non pagato";
                    } else {
                        status = "pagato";
                    }
                    if(billId!=null&&bullettinCode!=null&&year!=null&&description!=null&&expiringDate!=null&&amountDouble!=null) {
                    	Tax tax = new Tax(billId, bullettinCode, year, description,
                                expiringDate, amountDouble, status);
                        taxes.add(tax);
                    }
                }
            }
            return taxes;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return taxes;
        }
    }

}
