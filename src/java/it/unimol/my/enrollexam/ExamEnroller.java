/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.examsession.ExamSessionInfo;
import it.unimol.my.requesterhtml.HTMLRequesterException;
import it.unimol.my.requesterhtml.HTMLRequesterInterface;
import it.unimol.my.requesterhtml.HTMLRequesterManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Giuseppe
 */
public class ExamEnroller implements ExamEnrollerInterface {

    @Override
    public EnrolledExam enrollExam(ExamSessionInfo examSessionInfo, String username, String password, String careerId) throws UnirestException {
    	//Performs enrollment and gets the page
        Document doc = Jsoup.parse(getHtmlPage(examSessionInfo.getAction(), examSessionInfo.toHashMap(), username, password, careerId));
        String formAction = this.getEnrollFormAction(doc);
        Map enrollInfos = this.getEnrollInformation(doc);
        EnrolledExam enrolledExam = this.performEnrollment(username, password, careerId, formAction, enrollInfos);

        return enrolledExam;
    }

    private String getHtmlPage(String targetUrl, Map<String, Object> param, String username, String password, String careerId) throws UnirestException {
        HTMLRequesterInterface requester;
		try {
			requester = HTMLRequesterManager.getManager().getInstance(username, password, careerId);
		} catch (HTMLRequesterException e) {
			throw new UnirestException(e);
		}
        String html = null;
        try {
            targetUrl = ConfigurationManager.getInstance().getEsse3Base() + targetUrl;
            html = requester.post(new URL(targetUrl), param, username, password);
        } catch (MalformedURLException ex) {
            throw new UnirestException(ex);
        }
        return html;
    }

    private Map<String, String> getEnrollInformation(Document doc) {
        Map<String, String> infos = new HashMap<>();
        Elements inputs = doc.select("input[type=hidden]");
        for (Element e : inputs) {
            infos.put(e.attr("name"), e.val());
        }
        return infos;
    }

    private String getEnrollFormAction(Document doc) {
        Element form = doc.select("form").get(0);
        return form.attr("action");
    }

    private EnrolledExam performEnrollment(String username, String password, String careerId, String action, Map params) 
    		throws UnirestException{
    	String htmlPageString = this.getHtmlPage(action, params, username, password, careerId);
        Document doc = Jsoup.parse(htmlPageString);
        //System.out.println(doc);
        
        String message = doc.select("msg span").get(0).text();
        String messageUpperCase = message.toUpperCase();
        if (!messageUpperCase.contains("NON")) {
            Element infoTable = doc.select("table.detail_table").get(0);
            Element infoRow = infoTable.select("tr").get(1);
            Elements informations = infoRow.select("td");
            String date = informations.get(0).text();
            String room = informations.get(1).text();
            String professor = informations.get(2).text();
            String formAction = doc.select("form").get(0).attr("action");
            Map<String, String> infos = new HashMap<>();
            infos.put("action", formAction);
            Elements inputs = doc.select("input[type=hidden]");
            for (Element e : inputs) {
                infos.put(e.attr("name"), e.val());
            }
            return new EnrolledExam(date, room, professor, infos);
        } else {
            return null;
        }
    }
}
