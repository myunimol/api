package it.unimol.my.dettaglioappello;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Inserire descrizione classe
 * 
 * @author Pasquale Fornaro
 */
public class EnrolledExamSessionsExtractor implements EnrolledExamSessionsExtractorInterface {

	@Override
	public List<EnrolledExamSession> extractEnrolledExamSessions(String targetUrl,
			String username, String password) {
//		List<EnrolledExamSession> enrolledExamSessions = new ArrayList<EnrolledExamSession>();

		return this.stub();
	}

	private List<EnrolledExamSession> stub() {
		List<EnrolledExamSession> enrolledExamSessions = new ArrayList<EnrolledExamSession>();
		for (int i = 0; i < 3; i++) {
			EnrolledExamSession enrolledExamSession = new EnrolledExamSession(
					"Management 4", "6", "Matteo Merola", new Date(),
					new Date(), "Aula F", 2, 4, "");
			enrolledExamSessions.add(enrolledExamSession);
		}
		return enrolledExamSessions;
	}

}
