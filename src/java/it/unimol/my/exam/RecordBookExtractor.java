package it.unimol.my.exam;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa classe è il cuore del parsing del libretto. Fornisce i metodi da
 * richiamare per eseguire il parsing della pagina del libretto.
 *
 * @author Christian De Rita
 */
public class RecordBookExtractor implements RecordBookExtractorInterface {

	@Override
	public RecordBook getExamsList(String targetUrl, String username,
			String password) throws UnirestException {
		RecordBook recordBook = null;
		HTMLRequesterInterface requester = HTMLRequesterManager.getManager().getInstance(username, password);
		try {
			String html = requester.get(new URL(targetUrl), username, password);
			recordBook = this.extractRecordBook(html);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return recordBook;
	}

	/**
	 * Metodo privato che effettua l'estrazione dei dati necessari.
	 * 
	 * @param targetURL
	 *            La stringa che rappresenta l'url della pagina dalla quale
	 *            vengono estratte le informazioni
	 * @return L'oggetto RecordBook contenente tutte le informazioni relative al
	 *         libretto vituale.
	 */
	private RecordBook extractRecordBook(String targetURL) {
		List<Exam> exams = new ArrayList<Exam>();
		Document doc = Jsoup.parse(targetURL);
		Elements tplMasterCells = doc.select("td[class=tplMaster]");
		String averageString = "0", weightedAverageString = "0";
		if (tplMasterCells != null && tplMasterCells.size() == 2) {
			averageString = tplMasterCells.get(0).text();
			weightedAverageString = tplMasterCells.get(1).text();
			averageString = averageString.replaceAll("[^0-9\\.]", "");
			weightedAverageString = weightedAverageString.replaceAll(
					"[^0-9\\.]", "");
			averageString = averageString.substring(0,
					averageString.length() - 2);
			weightedAverageString = weightedAverageString.substring(0,
					weightedAverageString.length() - 2);
		}
		double average = Double.parseDouble(averageString);
		double weightedAverage = Double.parseDouble(weightedAverageString);
		Elements detailTableRows = doc.select("table[class=detail_table] tr");
		if (detailTableRows == null || detailTableRows.size() == 0) {
			return new RecordBook(exams, average, weightedAverage);
		}
		for (Element row : detailTableRows) {
			Elements cells = row.select("td");
			if (cells == null || cells.size() == 0) {
				continue;
			}
			Element link = cells.get(1).child(0);
			String linkString = link.attr("href");
			Pattern adsceIdPattern = Pattern.compile("adsce_id=[0-9]+\\&");
			Matcher m = adsceIdPattern.matcher(linkString);
			String adsceId = "";
			while (m.find()) {
				adsceId = m.group(0);
			}
			adsceId = adsceId.replace("adsce_id=", "");
			adsceId = adsceId.replace("&", "");

			String name = StringUtils.realTrim(link.text());

			String cfuString = "0";
			int cfu = 0;
			Element cfuCell = cells.get(6);
			if (cfuCell != null) {
				cfuString = StringUtils.realTrim(cfuCell.text());
				cfu = Integer.parseInt(cfuString);
			}
			String grade = "/";
			Date date = null;
			Element gradeDateCell = cells.get(9);
			if (gradeDateCell != null) {
				String gradeDate = StringUtils.realTrim(gradeDateCell.text());
				/**
				 * elimino il non-breaking space di html
				 * 
				 * @see http
				 *      ://stackoverflow.com/questions/1461907/html-encoding-
				 *      issues-Â-character-showing-up-instead-of-nbsp
				 */
				gradeDate = gradeDate.replace('\u00c2', '\u0020');
				gradeDate = gradeDate.replace('\u00a0', '\u0020');
				String[] bits = gradeDate.split(" - ");
				if (bits.length == 2) {
					grade = bits[0];
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					try {
						date = sdf.parse(bits[1]);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			String academicYear = "/";
			Element academicYearCell = cells.get(8);
			if (academicYearCell != null) {
				academicYear = StringUtils.realTrim(academicYearCell.text());
			}

			Exam exam = new Exam(name, cfu, grade, date, academicYear, adsceId);
			exams.add(exam);
		}
		RecordBook recordBook = new RecordBook(exams, average, weightedAverage);
		return recordBook;
	}

}
