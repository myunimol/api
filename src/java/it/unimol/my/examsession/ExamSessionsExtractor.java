package it.unimol.my.examsession;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.requesterhtml.HTMLRequester;
import it.unimol.my.requesterhtml.HTMLRequesterException;
import it.unimol.my.requesterhtml.HTMLRequesterInterface;
import it.unimol.my.requesterhtml.HTMLRequesterManager;
import it.unimol.my.utils.StringUtils;

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
			String username, String password, String pCareerId) throws UnirestException {
		List<DetailedExamSession> examSessions = null;
		List<ExamSessionInfo> examSessionsInfo = null;
		try {
			HTMLRequesterInterface requester;
			try {
				requester = HTMLRequesterManager.getManager().getInstance(username, password, pCareerId);
			} catch (HTMLRequesterException e) {
				throw new UnirestException(e.getMessage());
			}
			String html = requester.get(new URL(targetURL), username, password);
			// decommentare per testare in locale
			// html = requester
			// .get(new URL(
			// "http://localhost:8080/myunimol-webservices/pagine-target/elencoappelliUNIMOL.html"));
			examSessions = this.parseSessions(html);
			examSessionsInfo = this.parseSessionInfo(html);

			for (int i = 0; i < examSessionsInfo.size(); i++) {
				examSessions.get(i).setInfo(examSessionsInfo.get(i));
			}

			//Adds details to the sessions
			//TODO: move this portion of code in a new method
			int i = 0;
			for (ExamSessionInfo examSessionInfo : examSessionsInfo) {
				String targetPage = ConfigurationManager.getInstance()
						.getExamSessionDetailUrl();

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("APP_ID", examSessionInfo.getAppId());
				parameters.put("CDS_ESA_ID", examSessionInfo.getCdsEsaId());
				parameters.put("ATT_DID_ESA_ID", examSessionInfo.getAttDidEsaId());
				parameters.put("ADSCE_ID", examSessionInfo.getAdsceId());
				parameters.put("AA_OFF_ID", examSessionInfo.getAaOffId());
				parameters.put("CDS_ID", examSessionInfo.getCdsId());
				parameters.put("PDS_ID", examSessionInfo.getPdsId());
				parameters.put("AA_ORD_ID", examSessionInfo.getAaOrdId());
				parameters.put("ISCR_APERTA", examSessionInfo.getIscrAperta());
				parameters.put("TIPO_ATTIVITA", examSessionInfo.getTipoAttivita());

				String htmlDetails = requester.post(new URL(targetPage),
						parameters, username, password);
				
				loadExamDetails(examSessionInfo, examSessions.get(i), htmlDetails);
				i++;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnirestException(e);
		}
		return examSessions;
	}
	
	

	@Override
	public List<DetailedExamSession> getExamSessions(String targetURL) {
		List result = this.parseSessions(targetURL);
		return result;
	}
	
	private void loadExamDetails(ExamSessionInfo examSessionInfo, DetailedExamSession examSession, String htmlDetails) {
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
		examSession.setSessionType(sessionType);
		examSession.setProfessor(professor);
		examSession.setRoom(room);
		examSession.setEnrolled(enrolled);
		
		
		String tipoEsa = doc.select("input[name=TIPO_ESA]").get(0).val();
		String attDidId = doc.select("input[name=ATT_DID_ID]").get(0).val();
		String tipoIscr = doc.select("input[name=TIPO_ISCR]").get(0).val();
		String aaFreqId = doc.select("input[name=AA_FREQ_ID]").get(0).val();
		
		examSession.getInfo().setTipoEsa(tipoEsa);
		examSession.getInfo().setAttDidId(attDidId);
		examSession.getInfo().setTipoIscr(tipoIscr);
		examSession.getInfo().setAAFreqId(aaFreqId);
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
		Elements rows = table.select("tr");
		for (int i = 1; i < rows.size(); i++) {
			Elements cells = rows.get(i).select("td[class=detail_table]");
			String name = StringUtils.realTrim(cells.get(1).text());
			String dateString = StringUtils.realTrim(cells.get(2).text());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = sdf.parse(dateString);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			String window = cells.get(3).html();
			String[] bits = window.split("<br>");
			Date start = null;
			try {
				start = sdf.parse(StringUtils.realTrim(bits[0]));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			Date end = null;
			try {
				end = sdf.parse(StringUtils.realTrim(bits[1]));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			String notes = StringUtils.realTrim(cells.get(4).text());
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

		Elements tables = doc.select("table.detail_table");
		if (tables == null || tables.size() == 0) {
			return infoList;
		}
		Element table = tables.get(0);
		Elements rows = table.select("tr");
		for (int i = 1; i < rows.size(); i++) {
			Element cell = rows.get(i).select("td.detail_table").get(0);
			String appId = cell.select("input[name=APP_ID]").get(0).val();
			String cdsEsaId = cell.select("input[name=CDS_ESA_ID]").get(0)
					.val();
			String attDidEsaId = cell.select("input[name=ATT_DID_ESA_ID]")
					.get(0).val();
			String adSceId = cell.select("input[name=ADSCE_ID]").get(0).val();
			String aaOffId = cell.select("input[name=AA_OFF_ID]").get(0).val();
			String cdsId = cell.select("input[name=CDS_ID]").get(0).val();
			String pdsId = cell.select("input[name=PDS_ID]").get(0).val();
			String aaOrdId = cell.select("input[name=AA_ORD_ID]").get(0).val();
			String iscrAperta = cell.select("input[name=ISCR_APERTA]").get(0)
					.val();
			String tipoAttivita = cell.select("input[name=TIPO_ATTIVITA]")
					.get(0).val();
                        String tipoAppCod = cell.select("input[name=TIPO_APP_COD]")
					.get(0).val();
			Element form = cell.select("form").get(0);
			String action = form.attr("action");

			ExamSessionInfo examSessionInfo = new ExamSessionInfo(action,
					appId, cdsEsaId, attDidEsaId, adSceId, aaOffId, cdsId,
					pdsId, aaOrdId, iscrAperta, tipoAttivita, tipoAppCod);

			infoList.add(examSessionInfo);
		}
		return infoList;
	}
}
