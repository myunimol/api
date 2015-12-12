/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unimol.my.enrollexam;

import java.util.Map;

/**
 *
 * @author Giuseppe
 */
public class EnrolledExam {
    public String dateAndHour;
    public String roomAndBuilding;
    public String professor;
    public Map<String, String> examDetail;
    
    public EnrolledExam() {
    }

    public EnrolledExam(String dateAndHour, String roomAndBuilding, String professor, Map<String, String> examDetail) {
        this.dateAndHour = dateAndHour;
        this.roomAndBuilding = roomAndBuilding;
        this.professor = professor;
        this.examDetail = examDetail;
    }

    public String getDateAndHour() {
        return dateAndHour;
    }

    public void setDateAndHour(String dateAndHour) {
        this.dateAndHour = dateAndHour;
    }

    public String getRoomAndBuilding() {
        return roomAndBuilding;
    }

    public void setRoomAndBuilding(String roomAndBuilding) {
        this.roomAndBuilding = roomAndBuilding;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Map<String, String> getExamDetail() {
        return examDetail;
    }

    public void setExamDetail(Map<String, String> examDetail) {
        this.examDetail = examDetail;
    }
}
