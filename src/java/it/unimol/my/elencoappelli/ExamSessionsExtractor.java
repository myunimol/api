package it.unimol.my.elencoappelli;

import it.unimol.my.requesterhtml.HTMLRequester;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Questa classe è il cuore del parsing della lista degli appelli. Fornisce i
 * metodi da richiamare per eseguire il parsing della pagina della lista degli
 * appelli d'esame disponibili.
 *
 * @author Giuseppe Bianco
 */
public class ExamSessionsExtractor implements ExamSessionsExtractorInterface {

	@Override
	public List<ExamSession> getExamSessions(String targetURL, String username,
			String password) throws UnirestException {
		List<ExamSession> examSessions = null;
		try {
			HTMLRequester requester = new HTMLRequester();
			String html = requester.get(new URL(targetURL), username, password);
			// per estrarre da webserver in locale
//			String html = requester.get(new URL(targetURL));
			examSessions = this.parseSessions(html);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return examSessions;
	}

	@Override
	public List<ExamSession> getExamSessions(String targetURL) {
		List result = this.parseSessions(targetURL);
		return result;
	}

	/**
	 * Metodo PROVVISIORIO per estrarre il corpo di un file come stringa.
	 * Utilizzato per testare il funzionamento della componente. Andrà
	 * sostituito/eliminato una volta che la componente HttpRequester sarà
	 * completata.
	 * 
	 * @param pTarget
	 *            Path del file di cui si vuole estrarre il conenuto
	 * @return Stringa contenente l'intero contenuto del file preso in input
	 * @throws IOException
	 */
	private String getHtmlToString(String pTarget) throws IOException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(pTarget));
		} catch (FileNotFoundException ex) {
			return "Non ho trovato il file";
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	/**
	 * Metodo che implementa l'effettivo algoritmo per il parsing della pagina
	 * della lista degli appelli d'esame. Scorre l'intera tabella contenente le
	 * informazioni di ogni singolo appello d'esame, e crea un oggetto
	 * ExamSession (con tutte le informazioni) per ogni appello disponibile. La
	 * chiave del parsing è la classe attribuita all'elemento table:
	 * .detail_table Tutti gli appelli vengono inseriti in una Lista.
	 * 
	 * @param urlTarget
	 *            URL/PATH della pagina in cui è presente la tabella della lista
	 *            delle sessioni d'esame
	 * @return Restituisce una List di ExamSession contenente ogni appello
	 *         disponibile.
	 */
	private List<ExamSession> parseSessions(String html) {
		List<ExamSession> eSession = new ArrayList<ExamSession>();
		Document doc = Jsoup.parse(html);
		Elements tables = doc.select("table[class=detail_table]");
		if (tables.size()==0) {
			// non esiste la tabella quindi non ci sono sessioni d'esame
			return eSession;
		}
		Element table = tables.get(0);
		Iterator<Element> ite = table.select("td[class=detail_table]")
				.iterator();
		while (ite.hasNext()) {
			String link = ite.next().text();
			String materia = ite.next().text();
			String data = ite.next().text();
			String iscrizione = ite.next().text();
			String descrizione = ite.next().text();
			String sessioni = ite.next().text();
			String docenti = ite.next().text();
			String iscritti = ite.next().text();
			String cfu = ite.next().text();
			eSession.add(new ExamSession(materia, data, iscrizione,
					descrizione, sessioni, docenti, iscritti, cfu));
		}
		return eSession;
	}

}
