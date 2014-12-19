package it.unimol.my.taxes;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.List;

/**
 * Interfaccia dell'estrattore delle tasse universitarie
 * 
 * @author Carlo Branca
 */
public interface TaxesExtractorInterface {

    public List<Tax> getTaxesList(String targetURL, String username,
			String password) throws UnirestException;
    
}
