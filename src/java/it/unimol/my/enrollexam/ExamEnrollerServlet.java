/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

import it.unimol.my.examsession.ExamSessionInfo;
import it.unimol.my.utils.Esse3AuthServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Giuseppe
 */
@WebServlet(name = "EsamSessionEnrollmentServlet", urlPatterns = {"/enrollExam"})
public class ExamEnrollerServlet extends Esse3AuthServlet {

    @Override
    protected void serve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        //Uncomment to see html content
        //resp.setContentType("text/html");
        
        try {
	        ExamSessionInfo examInfo = gson.fromJson(req.getParameter("exam-id"), ExamSessionInfo.class);
	        ExamEnrollerInterface enroller = ExamEnrollerManager.getExamEnroller();
	        EnrolledExam enrolledExam = enroller.enrollExam(examInfo, username, password, careerId);
	        if (enrolledExam != null) {
	            String json = gson.toJson(enrolledExam);
	            writer.println("{\"result\":\"success\",\"exam\":" + json + "}");
	        } else {
	            writer.println("{\"result\":\"failure\",\"exam\":\"null\"}");
	        }
        } catch (UnirestException e) {
			String unirestExceptionMsg = config.getMessage("unirestException");
			writer.println("{\"result\":\"failure\", \"msg\":\"" + unirestExceptionMsg + "\"}");
		}
    }
}
