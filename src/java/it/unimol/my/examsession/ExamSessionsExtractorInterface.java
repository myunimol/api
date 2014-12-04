package it.unimol.my.examsession;

import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa classe implementa l'interfaccia per tutte le classi addette al parsing
 * della lista degli appelli. Dispone di due metodi per poter fare parsing di
 * pagine protette o non protette da login.
 * 
 * @author Giuseppe
 */
public interface ExamSessionsExtractorInterface {

	/**
	 * Metodo che permette di fare parsing di una pagina protetta da login
	 * 
	 * @param targetURL
	 *            URL della pagina su cui eseguire l'estrazione
	 * @param username
	 *            Username dell'utente che accede alla pagina protetta
	 * @param password
	 *            Password dell'utente che accede alla pagina protetta
	 * @return Una lista di oggetti "ExamSession" che rappresentano tutti gli
	 *         appelli disponibili al momento della richiesta
	 */
	public List<DetailedExamSession> getExamSessions(String targetURL, String username,
			String password) throws UnirestException;

	/**
	 * Metodo che permette di fare parsing di una pagina non protetta
	 * 
	 * @param targetURL
	 *            URL della pagina su cui eseguire l'estrazione
	 * @return Una lista di oggetti "ExamSession" che rappresentano tutti gli
	 *         appelli disponibili al momento della richiesta
	 */
	public List<DetailedExamSession> getExamSessions(String targetURL) throws UnirestException;

}
