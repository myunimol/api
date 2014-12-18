package it.unimol.my.unimolnews;

import java.util.ArrayList;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa classe implementa l'interfaccia per l'estrattore delle news
 * dell'Unimol.
 * 
 * @author Giuseppe Bianco
 */
public interface NewsExtractorInterface {

	/**
	 * Metodo per estrarre le news dai feed RSS dell'Unimol. Il risultato �� una
	 * lista di SingleUnimolNews con la seguente struttura:
	 * {autore,link,titolo,contenuto,data di pubblicazione}
	 * 
	 * @return ArrayList di SingleUnimolNews. L'ordinamento �� lo stesso dei
	 *         feed RSS dell'Unimol.
	 */
	public ArrayList<News> getUniversityNews() throws UnirestException;

	/**
	 * Metodo per estrarre le news dai feed RSS dei dipartimenti. Il risultato
	 * �� una lista di SingleDepartmentNews con la seguente struttura: {titolo,
	 * link, contenuto}
	 * 
	 * @param departmentFeedURL
	 *            URL dei feed RSS del dipartimento.
	 * @return ArrayList di SingleUnimolNews. L'ordinamento �� lo stesso dei
	 *         feed RSS del dipartimento.
	 */
	public ArrayList<News> getDepartmentNews(String departmentFeedURL) throws UnirestException;

}
