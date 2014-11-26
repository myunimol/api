package it.unimol.my.login;

/**
 * Classe che rappresenta le informazioni dell'utente recuperate dopo il login
 * 
 * @author Ivan Di Rienzo
 */
public class UserInformation {
    
    private String nome;
    private String cognome;
    private String studentID;
    private String studentClass;
    //TODO aggiungere gli altri campi utili: appelli disponibili,tasse etc....
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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
    
    
}
