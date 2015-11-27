package it.unimol.my.careers;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.requesterhtml.HTMLRequesterException;
import it.unimol.my.requesterhtml.HTMLRequesterInterface;
import it.unimol.my.requesterhtml.HTMLRequesterManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Classe che fa il parser per il login di esse3 Unimol
 *
 * @author Ivan Di Rienzo
 */
public class UnimolCareersParser implements CareersParser {
    public CareersInfo getCareersIds(String username, String password) throws UnirestException {

    	HTMLRequesterInterface requester;
    	try {
    		requester = HTMLRequesterManager.getManager().getInstance(username, password, HTMLRequesterManager.NO_CAREER_ACCESS);
    	} catch (HTMLRequesterException e) {
    		throw new UnirestException(e.getMessage());
    	}
    	
        try {
        	URL url = new URL(ConfigurationManager.getInstance().getCareerListUrl());
        	String response = requester.get(url, username, password);
        	Document doc = Jsoup.parse(response);
        	
            if (!this.isLogged(doc)) {
                return null; // login non riuscito
            } else {
            	return new CareersInfo(getCareers(doc));
            }

        } catch (MalformedURLException ex) {
            return null;
        }

    }

    /**
     * Metodo privato che controlla se il login ha avuto successo
     *
     * @param doc il Document da parsare
     * @return TRUE se il login ha avuto successo FALSE altrimenti
     */
    private boolean isLogged(Document doc) {
        Element firstMeta = doc.select("meta").first();

        /* quando il login non ha successo si ottiene una pagina con tag meta
         * per il redirect
         */
        if (firstMeta.attr("http-equiv").equals("refresh")) {
            // login non riuscito
            return false;
        }

        return true;
    }
    
    
    private boolean haveManyCareers(Document doc) {
        Element elementPageTitle = doc.select("div[class=titolopagina]").first();
        if (elementPageTitle != null) {
            String pageTitle = elementPageTitle.text();
            if (pageTitle != null) {
                if (pageTitle.equals("Scegli carriera")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private List<CareerInfo> getCareers(Document doc) throws UnirestException {
        List<CareerInfo> result = new ArrayList<>();
        
        Element detailTable = doc.select("table[class=detail_table]").first();
        if(detailTable != null) {
            Elements tableRows = detailTable.select("tr");
            if(tableRows != null) {
        		for (Element tdDetailTable : tableRows) {
        			Elements currentRow = tdDetailTable.select("td");
        			if (currentRow != null && currentRow.size() > 0) {
        				String matricola 		= currentRow.get(0).select("a").text();
        				String tipoCorso 		= currentRow.get(1).select("a").text();
        				String corsoDiStudio	= currentRow.get(2).select("a").text();
        				String stato			= currentRow.get(3).select("a").text();
        				
		        		String urlCareer 		= currentRow.select("a").first().attr("abs:href");
		        		
		        		// Extracts the career ID
		        		String parameterName = "stu_id=";
		        		String careerId = "";
		        		for (int index = urlCareer.indexOf(parameterName) + parameterName.length(); 
		        				index < urlCareer.length() && urlCareer.charAt(index) <= '9' && urlCareer.charAt(index) >= '0'; 
		        				index++) {
		        			careerId = careerId + urlCareer.charAt(index);
		        		}
		        		
		        		CareerInfo info = new CareerInfo();
		        		info.setMatricola(matricola);
		        		info.setTipoCorso(tipoCorso);
		        		info.setCorsoDiStudio(corsoDiStudio);
		        		info.setStato(stato);
		        		info.setId(careerId);
		        		
		        		//Adds the ID to the result list
		    			result.add(info);
        			}
        		}
            }
        }
        
        return result;
    }
}
