/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.examsession.DetailedExamSession;
import it.unimol.my.examsession.ExamSessionInfo;
import it.unimol.my.requesterhtml.HTMLRequesterInterface;
import it.unimol.my.requesterhtml.HTMLRequesterManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.Configuration;

/**
 *
 * @author Giuseppe
 */
public class ExamEnroller implements ExamEnrollerInterface {

    @Override
    public boolean enrollExam(ExamSessionInfo examSessionInfo, String username, String password) {
        boolean result = false;

        return result;
    }

    @Override
    public String getHtmlPage(ExamSessionInfo examInfo, String username, String password) {
        Map map = examInfo.toHashMap();
        HTMLRequesterInterface requester = HTMLRequesterManager.getManager().getInstance(username, password);
        String html = null;
        String targetUrl = ConfigurationManager.getInstance().getEsse3Base() + examInfo.getAction();

        try {
            html = requester.post(new URL(targetUrl), map);
        } catch (UnirestException ex) {
            Logger.getLogger(ExamEnroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ExamEnroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return html;
    }
}
