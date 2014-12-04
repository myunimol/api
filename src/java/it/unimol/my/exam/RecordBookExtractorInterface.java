package it.unimol.my.exam;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa classe implementa l'interfaccia per tutte le classi addette al parsing
 * del libretto. Dispone di un metodo per poter fare parsing di pagine protette.
 * 
 * @author Christian De Rita
 */
public interface RecordBookExtractorInterface {

	/**
	 * Metodo che permette di fare l'estrazione del libretto
	 * 
	 * @param targetUrl
	 * @param username
	 * @param password
	 * @return RecordBook Il libretto degli esami
	 */
	public RecordBook getExamsList(String targetUrl, String username,
			String password) throws UnirestException;

}