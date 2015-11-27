package it.unimol.my.careers;

import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Interfaccia del parser per le informazioni del login
 * 
 * @author Ivan Di Rienzo
 */
public interface CareersParser {
    
    /**
     * Effettua il parser della pagina di login recuperando le informazioni dell'utente,
     * restituisce NULL in caso di errato login (evitato l'uso di eccezioni per ottimizare prestazioni)
     * 
     * @param username nome utente per accedere ad esse3
     * @param password password per accedere ad esse3
     * @return Se il login ha successo restituisce le informazione dello studente, null altrimenti
     */
    public CareersInfo getCareersIds(String username, String password) throws UnirestException;
}

