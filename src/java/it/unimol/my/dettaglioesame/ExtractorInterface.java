package it.unimol.my.dettaglioesame;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Inserire descrizione classe
 *
 * @author Matteo Ianno
 */
public interface ExtractorInterface {

    /**
     * Metodo che permette di fare l'estrazione del dettaglio di un esame
     *
     * @param urlTarget URL della pagina su cui eseguire il parser
     * @return DetailedExam Dettaglio esame
     */
    public DetailedExam getDetails(String urlTarget, String examId);

    /**
     * Metodo che permette di fare l'estrazione del dettaglio di un esame
     * protetto da login
     *
     * @param urlServlet URL della pagina su cui eseguire il parser
     * @param username
     * @param password
     * @return DetailedExam Dettaglio esame
     */
    public DetailedExam getDetails(String urlServlet, String username, String password, String examId) throws UnirestException;

}
