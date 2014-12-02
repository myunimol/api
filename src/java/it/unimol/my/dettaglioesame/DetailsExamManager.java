package it.unimol.my.dettaglioesame;

/**
 * Inserire descrizione classe
 * 
 * @author Matteo Ianno
 */
public class DetailsExamManager {

    /**
     * Descrizione variabile
     */
    private static ExtractorInterface INSTANCE;

   
	/**
	 * Descrizione metodo statico
	 * @return Una istanza dell'estrattore del dettaglio esame
	 */
	public static ExtractorInterface getRecordBookExtractor() {
		if (DetailsExamManager.INSTANCE == null) {
			DetailsExamManager.INSTANCE = new DetailsExtractor();
		}
		return DetailsExamManager.INSTANCE;
	}
}
