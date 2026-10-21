package org.example.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name ="passport")
public class Passport {

    @Id
    private int passportId;
    private  String passportNumber;
    public Passport(){}
    public Passport(int passportId, String passportNumber){
        this.passportId = passportId;
        this.passportNumber = passportNumber;
    }
    public int getPassportId() {
        return passportId;
    }
    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}