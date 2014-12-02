package it.unimol.my.elencoesami;

/**
 * Inserire descrizione classe
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
			String password);

}