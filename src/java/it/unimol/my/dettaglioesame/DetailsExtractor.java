package it.unimol.my.dettaglioesame;

import it.unimol.my.elencoesami.Exam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Inserire descrizione classe
 * 
 * @author Matteo Ianno
 */
public class DetailsExtractor implements ExtractorInterface{
    
    @Override
    public DetailedExam getDetails(String urlTarget) {
    //Tutto il parsing qui
    return this.stub();
    }

    @Override
    public DetailedExam getDetails(String urlServlet, String username, String password) {
        //Tutto il parsing qui
    return this.stub();
    }
    
    private DetailedExam stub() {
        
        List<Details> detailList = new ArrayList<Details>();
		for (int i = 0; i < 5; i++) {
			Details details = new Details("nome modulo", "3", 36, "Informatica");                        
			detailList.add(details);
		}
                DetailedExam exam = new DetailedExam("esame farlocco", "6", "18", new Date(),
					"2014/2015", detailList);
        return exam;
    }

    
}
