package it.unimol.my.dettaglioesame;

/**
 * Inserire descrizione classe
 *
 * @author Matteo Ianno
 */
public class Details {

    /**
     * Descrizione variabile
     */
    private String moduleName;

    /**
     * Descrizione variabile
     */
    private String cfu;

    /**
     * Descrizione variabile
     */
    private int hours;

    /**
     * Descrizione variabile
     */
    private String area;

    /**
     * Descrizione costruttore
     *
     * @param moduleName
     * @param cfu
     * @param hours
     * @param area
     */
    public Details(String moduleName, String cfu, int hours, String area) {
        this.moduleName = moduleName;
        this.cfu = cfu;
        this.hours = hours;
        this.area = area;
    }

    /**
     * Descrizione costruttore
     */
    public Details() {

    }

    /**
     * @return the module's name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName the module's name to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return the cfu
     */
    public String getCfu() {
        return cfu;
    }

    /**
     * @param cfu the cfu to set
     */
    public void setCfu(String cfu) {
        this.cfu = cfu;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.moduleName != null ? this.moduleName.hashCode() : 0);
        hash = 29 * hash + (this.cfu != null ? this.cfu.hashCode() : 0);
        hash = 29 * hash + this.hours;
        hash = 29 * hash + (this.area != null ? this.area.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Details other = (Details) obj;
        if ((this.moduleName == null) ? (other.moduleName != null) : !this.moduleName.equals(other.moduleName)) {
            return false;
        }
        if ((this.cfu == null) ? (other.cfu != null) : !this.cfu.equals(other.cfu)) {
            return false;
        }
        if (this.hours != other.hours) {
            return false;
        }
        if ((this.area == null) ? (other.area != null) : !this.area.equals(other.area)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Details{" + "moduleName=" + moduleName + ", cfu=" + cfu + ", hours=" + hours + ", area=" + area + '}';
    }
    
    
}
