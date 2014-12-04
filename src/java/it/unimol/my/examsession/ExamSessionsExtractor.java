package it.unimol.my.examsession;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.requesterhtml.HTMLRequester;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public List<DetailedExamSession> getExamSessions(String targetURL,
			String username, String password) throws UnirestException {
		List<DetailedExamSession> examSessions = null;
		List<ExamSessionInfo> examSessionsInfo = null;
		try {
			HTMLRequester requester = new HTMLRequester();
			String html = requester.get(new URL(targetURL), username, password);
			// decommentare per testare in locale
			// String html = requester.get(new URL(targetURL));
			examSessions = this.parseSessions(html);
			examSessionsInfo = this.parseSessionInfo(html);

			for (int i = 0; i < examSessionsInfo.size(); i++) {
				examSessions.get(i).setInfo(examSessionsInfo.get(i));
			}

			int i = 0;
			for (ExamSessionInfo examSessionInfo : examSessionsInfo) {

				String targetPage = ConfigurationManager.getInstance()
						.getExamSessionDetailUrl();

				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("APP_ID", examSessionInfo.getAppId());
				parameters.put("CDS_ESA_ID", examSessionInfo.getCdsEsaId());
				parameters.put("ATT_DID_ESA_ID",
						examSessionInfo.getAttDidEsaId());
				parameters.put("ADSCE_ID", examSessionInfo.getAdsceId());
				parameters.put("AA_OFF_ID", examSessionInfo.getAaOffId());
				parameters.put("CDS_ID", examSessionInfo.getCdsId());
				parameters.put("PDS_ID", examSessionInfo.getPdsId());
				parameters.put("AA_ORD_ID", examSessionInfo.getAaOrdId());
				parameters.put("ISCR_APERTA", examSessionInfo.getIscrAperta());
				parameters.put("TIPO_ATTIVITA",
						examSessionInfo.getTipoAttivita());
				parameters.put("TIPO_APP_COD", examSessionInfo.getTipoAppCod());

				String htmlDetails = requester.post(new URL(targetPage),
						parameters, username, password);
				Document doc = Jsoup.parse(htmlDetails);
				Elements tdsTplForm = doc.select("td[class=tplForm]");
				String sessionType = "/";
				if (tdsTplForm.get(2) != null) {
					sessionType = tdsTplForm.get(2).text();
				}
				Elements tdsDetailTable = doc.select("td[class=detail_table]");
				String professor = "/";
				String room = "/";
				String enrolled = "/";
				if (tdsDetailTable.get(1) != null) {
					room = tdsDetailTable.get(1).text();
				}
				if (tdsDetailTable.get(2) != null) {
					enrolled = tdsDetailTable.get(2).text();
				}
				if (tdsDetailTable.get(3) != null) {
					professor = tdsDetailTable.get(3).text();
				}
				examSessions.get(i).setSessionType(sessionType);
				examSessions.get(i).setProfessor(professor);
				examSessions.get(i).setRoom(room);
				examSessions.get(i).setEnrolled(enrolled);
				i++;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return examSessions;
	}

	@Override
	public List<DetailedExamSession> getExamSessions(String targetURL) {
		List result = this.parseSessions(targetURL);
		return result;
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
	private List<DetailedExamSession> parseSessions(String html) {
		List<DetailedExamSession> eSession = new ArrayList<DetailedExamSession>();
		Document doc = Jsoup.parse(html);
		Elements tables = doc.select("table[class=detail_table]");
		if (tables.size() == 0) {
			// non esiste la tabella quindi non ci sono sessioni d'esame
			return eSession;
		}
		Element table = tables.get(0);
		Iterator<Element> ite = table.select("td[class=detail_table]")
				.iterator();
		while (ite.hasNext()) {
			// skippo il link
			ite.next();
			String name = ite.next().text();
			String dateString = ite.next().text();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = sdf.parse(dateString);
			} catch (ParseException ex) {
				ex.printStackTrace();
				date = new Date();
			}
			String window = ite.next().toString();
			String[] bits = window.split("<br>");
			Date start = null;
			try {
				start = sdf.parse(bits[0]);
			} catch (ParseException ex) {
				ex.printStackTrace();
				start = new Date();
			}
			Date end = null;
			try {
				end = sdf.parse(bits[1]);
			} catch (ParseException ex) {
				ex.printStackTrace();
				end = new Date();
			}
			String notes = ite.next().text();

			DetailedExamSession examSession = new DetailedExamSession();
			examSession.setName(name);
			examSession.setDate(date);
			examSession.setExpiringDate(end);
			examSession.setNotes(notes);

			eSession.add(examSession);
		}
		return eSession;
	}

	/**
	 * Metodo che implementa l'algoritmo per il parsing delle informazioni
	 * nascoste di ogni appello disponibile. Queste informazioni sono una serie
	 * di codici che identificano univocamente ogni appello d'esame disponibile.
	 * Servono per poter andare nella pagina di prenotazione dell'appello a cui
	 * si riferiscono.
	 *
	 * @param urlTarget
	 *            URL/PATH della pagina in cui è presente la tabella della lista
	 *            delle sessioni d'esame
	 * @return Restituisce una ArrayList di ExamSessionsInfo. Ogni elemento è
	 *         l'insieme delle informazioni di ogni appello d'esame.
	 */
	private List<ExamSessionInfo> parseSessionInfo(String html) {
		ArrayList<ExamSessionInfo> infoList = new ArrayList<ExamSessionInfo>();
		Document doc = Jsoup.parse(html);
		Elements form = doc.select("td[colspan=2]");
		Iterator<Element> info = form.select("input[type=hidden]").iterator();
		String action = form.select("form[method=post]").attr("action");
		while (info.hasNext()) {
			String appId = info.next().val();
			String cdsEsaId = info.next().val();
			String attDidEsaId = info.next().val();
			String adsceId = info.next().val();
			String aaOffId = info.next().val();
			String cdsId = info.next().val();
			String pdsId = info.next().val();
			String aaOrdId = info.next().val();
			String iscrAperta = info.next().val();
			String tipoAttivita = info.next().val();
			String tipoAppCod = info.next().val();
			infoList.add(new ExamSessionInfo(action, appId, cdsEsaId,
					attDidEsaId, adsceId, aaOffId, cdsId, pdsId, aaOrdId,
					iscrAperta, tipoAttivita, tipoAppCod));
		}
		return infoList;
	}
}
