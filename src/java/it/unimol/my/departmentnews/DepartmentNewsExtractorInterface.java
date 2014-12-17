package it.unimol.my.departmentnews;

import java.util.ArrayList;

/**
 * Questa classe implementa l'interfaccia per l'estrattore delle news dei
 * dipartimenti.
 * 
 * @author Giuseppe Bianco
 */
public interface DepartmentNewsExtractorInterface {

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
	public ArrayList<SingleDepartmentNews> getDepartmentNews(
			String departmentFeedURL);

}
