package it.unimol.my.taxes;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.List;

/**
 * Interfaccia dell'estrattore delle tasse universitarie
 * 
 * @author Carlo Branca
 */
public interface TaxesExtractorInterface {
    
    /**
     * Metodo per l'estrazione delle tasse
     * 
     * @param targetURL Url della pagina tasse di esse3
     * @param username Username dell'utente
     * @param password Password dell'utente
     * @return La lista delle tasse
     * @throws UnirestException 
     */
    public List<Tax> getTaxesList(String targetURL, String username,
			String password, String pCareerId) throws UnirestException;
    
}
