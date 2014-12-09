package it.unimol.my.addressbook;

import java.util.Objects;

/**
 * Contact è il bean del singolo contatto della rubrica
 *
 * @author Carlo Branca
 */
public class Contact {
    
    private String fullname;
    private String role;
    private String building;
    private String internalTelephone;
    private String externalTelephone;
    private String email;

    public Contact() {
        
    }
    
    public Contact(String fullname, String role, String building, String internalTelephone, String externalTelephone, String email) {
        this.fullname = fullname;
        this.role = role;
        this.building = building;
        this.internalTelephone = internalTelephone;
        this.externalTelephone = externalTelephone;
        this.email = email;
    }   

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getInternalTelephone() {
        return internalTelephone;
    }

    public void setInternalTelephone(String internalTelephone) {
        this.internalTelephone = internalTelephone;
    }

    public String getExternalTelephone() {
        return externalTelephone;
    }

    public void setExternalTelephone(String externalTelephone) {
        this.externalTelephone = externalTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" + "fullname=" + fullname + ", role=" + role + ", building=" + building + ", internalTelephone=" + internalTelephone + ", externalTelephone=" + externalTelephone + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.fullname);
        hash = 37 * hash + Objects.hashCode(this.role);
        hash = 37 * hash + Objects.hashCode(this.building);
        hash = 37 * hash + Objects.hashCode(this.internalTelephone);
        hash = 37 * hash + Objects.hashCode(this.externalTelephone);
        hash = 37 * hash + Objects.hashCode(this.email);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.building, other.building)) {
            return false;
        }
        if (!Objects.equals(this.internalTelephone, other.internalTelephone)) {
            return false;
        }
        if (!Objects.equals(this.externalTelephone, other.externalTelephone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    
    
}
