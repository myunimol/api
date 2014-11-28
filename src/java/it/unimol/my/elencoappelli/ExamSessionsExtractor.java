package it.unimol.my.elencoappelli;

import java.util.ArrayList;
import java.util.List;

/**
 * Descrizione accurata classe!!!
 * 
 * @author Giuseppe Bianco
 */
public class ExamSessionsExtractor implements ExamSessionsExtractorInterface {

	@Override
	public List<ExamSession> getExamSessions(String targetURL, String username,
			String password) {
		List<ExamSession> examSessions = new ArrayList<ExamSession>();
		// TODO inserire tutto il parsing qui
		return examSessions;
	}

}
