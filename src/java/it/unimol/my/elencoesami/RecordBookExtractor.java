package it.unimol.my.elencoesami;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Inserire descrizione classe
 * 
 * @author Christian De Rita
 */
public class RecordBookExtractor implements RecordBookExtractorInterface {

	@Override
	public RecordBook getExamsList(String targetUrl, String username,
			String password) {
		// TODO Auto-generated method stub
		return this.stub();
	}

	private RecordBook stub() {
		List<Exam> exams = new ArrayList<Exam>();
		for (int i = 0; i < 5; i++) {
			Exam exam = new Exam("esame farlocco", "6", "18", new Date(),
					"2014/2015");
			exams.add(exam);
		}
		RecordBook recordBook = new RecordBook(exams, 18.0, 18.0);
		return recordBook;
	}
}
