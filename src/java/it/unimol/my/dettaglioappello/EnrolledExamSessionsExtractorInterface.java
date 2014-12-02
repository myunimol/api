package it.unimol.my.dettaglioappello;

import java.util.List;

/**
 * Inserire descrizione classe
 * 
 * @author Pasquale Fornaro
 */
public interface EnrolledExamSessionsExtractorInterface {

	/**
	 * Metodo che permette di fare parsing di una pagina protetta da login
	 * 
	 * @param credentials
	 *            Credenziali per accedere alla pagina
	 * @param urlServlet
	 *            URL della pagina su cui eseguire il parser
	 * @return
	 */
	public List<EnrolledExamSession> extractEnrolledExamSessions(String targetUrl,
			String username, String password);

}
