package it.unimol.my.examsession;

import it.unimol.my.requesterhtml.HTMLRequester;
import it.unimol.my.requesterhtml.HTMLRequesterInterface;
import it.unimol.my.requesterhtml.HTMLRequesterManager;
import it.unimol.my.utils.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa classe fornisce un oggetto concreto che effettua l'estrazione dei dati
 * relativi agli appelli prenotati.
 *
 */
public class EnrolledExamsExtractor implements EnrolledExamsExtractorInterface {

	@Override
	public List<EnrolledExamSession> getEnrolledExamSessions(String targetURL,
			String username, String password) throws UnirestException {
		List<EnrolledExamSession> enrolledExams = new ArrayList<EnrolledExamSession>();
		HTMLRequesterInterface requester = HTMLRequesterManager.getManager().getInstance(username, password);
		// String html = requester.get(new URL(targetURL), username,
		// password);
		// decommentare per testare in locale
		String html = null;
		try {
			html = requester.get(new URL(targetURL), username, password);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return enrolledExams;
		}

		Document doc = Jsoup.parse(html);
		Elements detailTables = doc.select("table[class=detail_table]");
		if (detailTables == null || detailTables.size() == 0) {
			return enrolledExams;
		}
		for (Element detailTable : detailTables) {
			Elements tableRows = detailTable.select("tr");
			if (tableRows == null || tableRows.size() <= 2) {
				continue;
			}
			Element nameHeader = tableRows.get(0);
			String name = "/";
			if (nameHeader != null) {
				name = StringUtils.realTrim(nameHeader.select("th").text());
			}
			Element enrollmentHeader = tableRows.get(1);
			String enrollmentPosition = "/";
			String enrolled = "/";
			if (enrollmentHeader != null) {
				String enrollmentString = enrollmentHeader.select("th").text();
				enrollmentString = enrollmentString.replaceFirst(
						"Numero Iscrizione: ", "");
				String[] bits = enrollmentString.split(" su ");
				if (bits.length == 2) {
					enrollmentPosition = bits[0];
					enrolled = bits[1];
				}
			}
			Element contentRow = tableRows.get(5);
			if (contentRow == null) {
				continue;
			}
			Elements contentCells = contentRow.select("td");
			if (contentCells == null || contentCells.size() != 8) {
				continue;
			}
			String dateString = contentCells.get(0).text();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String hour = StringUtils.realTrim(contentCells.get(1).text());
			Date date = null;
			try {
				date = sdf.parse(dateString+" "+hour);
			} catch (ParseException ex) {
				ex.printStackTrace();
				date = new Date();
			}
			
			String building = StringUtils.realTrim(contentCells.get(2).text());
			String room = StringUtils.realTrim(contentCells.get(3).text());
			String notes = StringUtils.realTrim(contentCells.get(4).text());
			String firstProfessor = StringUtils.realTrim(contentCells.get(5).text());
			List<String> professors = new ArrayList<String>();
			professors.add(firstProfessor);
			// continuo nelle righe della tabella (dalla 6^ in poi - se
			// esiste)
			for (int i = 6; i < tableRows.size(); i++) {
				Element professorRow = tableRows.get(i);
				if (professorRow != null) {
					Elements profRowCells = professorRow.select("td");
					if (profRowCells.size() == 1) {
						Element profCell = profRowCells.get(0);
						if (profCell != null) {
							professors.add(profCell.text());
						}
					}
				}
			}
			// creo l'elenco: Matteo Merola, Carlo branca,
			String professorList = "";
			for (String prof : professors) {
				professorList += prof + ", ";
			}
			// rimuovo l'ultimo ,
			professorList = professorList.substring(0,
					professorList.length() - 2);
			
			Elements inputs = contentCells.get(6).select("input");
			String id = "";
			for (Element input : inputs) {
				if(input.attr("name").equalsIgnoreCase("imageField") || input.attr("name").equalsIgnoreCase("return")) {
					continue;
				}
				id+=input.val()+"#";
			}
			// remove last '#'
			id = id.substring(0, id.length()-1);
			EnrolledExamSession enrolledExamSession = new EnrolledExamSession(
					name, date, room, professorList, notes, id, "0",
					enrollmentPosition, enrolled);
			enrolledExams.add(enrolledExamSession);
		}
		return enrolledExams;
	}
}
