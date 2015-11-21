/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.my.examsession.ExamSessionInfo;

/**
 *
 * @author Giuseppe
 */
public class ExamEnroller implements ExamEnrollerInterface{

    @Override
    public boolean enrollExam(ExamSessionInfo examSessionInfo, String username, String password) throws UnirestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
