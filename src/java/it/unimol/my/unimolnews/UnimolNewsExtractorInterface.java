package it.unimol.my.unimolnews;

import java.util.ArrayList;

/**
 * Questa classe implementa l'interfaccia per l'estrattore delle news
 * dell'Unimol.
 * 
 * @author Giuseppe Bianco
 */
public interface UnimolNewsExtractorInterface {

	/**
	 * Metodo per estrarre le news dai feed RSS dell'Unimol. Il risultato �� una
	 * lista di SingleUnimolNews con la seguente struttura:
	 * {autore,link,titolo,contenuto,data di pubblicazione}
	 * 
	 * @return ArrayList di SingleUnimolNews. L'ordinamento �� lo stesso dei
	 *         feed RSS dell'Unimol.
	 */
	public ArrayList<SingleUnimolNews> getUniversityNews();

}
