package it.unimol.my.dettaglioesame;

import java.util.Date;
import it.unimol.my.elencoesami.Exam;
import java.util.List;

public class DetailedExam extends Exam {

    /**
     * Descrizione variabile
     */
    private List<Details> detailsExam;

    /**
     * Descrizione costruttore
     *
     * @param name
     * @param cfu
     * @param grade
     * @param date
     * @param academicYear
     * @param detailsExam
     */
    public DetailedExam(String name, String cfu, String grade, Date date,
            String academicYear, List<Details> detailsExam) {
        super(name, cfu, grade, date, academicYear);
        this.detailsExam = detailsExam;
    }

    /**
     * Descrizione costruttore
     */
    public DetailedExam() {
    	super();
    }

    /**
     * @return the detailsExam
     */
    public List<Details> getDetailsExam() {
        return detailsExam;
    }

    /**
     * @param detailsExam the detailsExam to set
     */
    public void setDetailsExam(List<Details> detailsExam) {
        this.detailsExam = detailsExam;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.detailsExam != null ? this.detailsExam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (!super.equals(obj)) 
            return false;
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetailedExam other = (DetailedExam) obj;
        if (this.detailsExam != other.detailsExam && (this.detailsExam == null || !this.detailsExam.equals(other.detailsExam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"DetailedExam{" + "detailsExam=" + detailsExam + '}';
    }
    
    

}
