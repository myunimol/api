/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

/**
 *
 * @author Giuseppe
 */
public class ExamEnrollerManager {
    
    
    private static ExamEnrollerInterface INSTANCE;
    
    public static ExamEnrollerInterface getExamEnroller(){
        if (ExamEnrollerManager.INSTANCE == null) {
			ExamEnrollerManager.INSTANCE = new ExamEnroller();
		}
		return ExamEnrollerManager.INSTANCE;
    }
}
