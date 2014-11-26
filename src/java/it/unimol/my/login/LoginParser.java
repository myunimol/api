package it.unimol.my.login;

import java.net.URL;

/**
 * Interfaccia del parser per le informazioni del login
 * 
 * @author Ivan Di Rienzo
 */
public interface LoginParser {
    
    /**
     * Effettua il parser della pagina di login recuperando le informazioni dell'utente,
     * restituisce NULL in caso di errato login (evitato l'uso di eccezioni per ottimizare prestazioni)
     * 
     * @param urlLogin url per il login da cui fare il parsing
     * @param username nome utente per accedere ad esse3
     * @param password password per accedere ad esse3
     * @return Se il login ha successo restituisce le informazione dello studente, null altrimenti
     */
    public UserInformation getLoginInformation(URL urlLogin, String username, String password);
    
}
