/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.examsession.ExamSessionInfo;
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
    public EnrolledExam enrollExam(ExamSessionInfo examSessionInfo, String username, String password) {
        Document doc = Jsoup.parse(getHtmlPage(examSessionInfo.getAction(), examSessionInfo.toHashMap(), username, password));
        String formAction = this.getEnrollFormAction(doc);
        Map enrollInfos = this.getEnrollInformation(doc);
        EnrolledExam enrolledExam = this.performEnrollment(username, password, formAction, enrollInfos);

        return enrolledExam;
    }

    private String getHtmlPage(String targetUrl, Map<String, Object> param, String username, String password) {
        HTMLRequesterInterface requester = HTMLRequesterManager.getManager().getInstance(username, password);
        String html = null;
        try {
            targetUrl = ConfigurationManager.getInstance().getEsse3Base() + targetUrl;
            html = requester.post(new URL(targetUrl), param, username, password);
        } catch (UnirestException | MalformedURLException ex) {
            Logger.getLogger(ExamEnroller.class.getName()).log(Level.SEVERE, null, ex);
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

    private EnrolledExam performEnrollment(String username, String password, String action, Map<String, Object> params) {
        String htmlPageString = this.getHtmlPage(action, params, username, password);
        Document doc = Jsoup.parse(htmlPageString);
        String message = doc.select("msg span").get(0).text();
        String messageUpperCase = message.toUpperCase();
        if (messageUpperCase.contains("EFFETTUATA")) {
            Element infoTable = doc.select("table.detail_table").get(0);
            Element infoRow = infoTable.select("td").get(1);
            String date = infoRow.getElementsByTag("tr").get(0).text();
            String room = infoRow.getElementsByTag("tr").get(1).text();
            String professor = infoRow.getElementsByTag("tr").get(2).text();
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
