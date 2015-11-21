/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

import it.unimol.my.config.ConfigurationManager;
import it.unimol.my.utils.Esse3AuthServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Giuseppe
 */
@WebServlet(name = "EsamSessionEnrollmentServlet", urlPatterns = {"/enrollExam"})
public class ExamEnrollerServlet extends Esse3AuthServlet {

    @Override
    protected void serve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
	String parameters = req.getParameter("exam-info");	        
        ExamEnrollerInterface enroller = ExamEnrollerManager.getExamEnroller();
        
        //boolean examEnrolled = enroller.enrollExam(examSessionInfo, username, password);
        
        
    }
}
