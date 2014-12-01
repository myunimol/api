package it.unimol.my.login;

/**
 * Classe bean che rappresenta le informazioni dell'utente recuperate dopo il login
 * 
 * @author Ivan Di Rienzo
 */
public class UserInformation {
    
    /* result sarà sempre success dato che se l'oggetto viene creato vuol dire che il login ha avuto successo! 
     * serve a facilitare la generazione del JSON tramite Gson (pigrizia XD)
     */
    private String result = "success"; 
    
    private String name; //nome
    private String surname; //cognome
    private String studentID; //matricola
    private String studentClass; //es: INFORMATICA 3°Anno
    private String taxes; //situazione delle tasse
    private String careerPlan; //se il piano carriera è modificabile
    private String availableExams; // # appelli disponibili
    private String enrolledExams; // # esami prenotati

    
    /**
     * Bean class, costruttore vuoto
     */
    public UserInformation() {
    }

    
    //SOLO GETTER E SETTER

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getCareerPlan() {
        return careerPlan;
    }

    public void setCareerPlan(String careerPlan) {
        this.careerPlan = careerPlan;
    }

    public String getAvailableExams() {
        return availableExams;
    }

    public void setAvailableExams(String availableExams) {
        this.availableExams = availableExams;
    }

    public String getEnrolledExams() {
        return enrolledExams;
    }

    public void setEnrolledExams(String enrolledExams) {
        this.enrolledExams = enrolledExams;
    }
    
    
    
    
}
