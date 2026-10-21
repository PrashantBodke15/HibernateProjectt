package org.example.model;
import jakarta.persistence.*;
@Entity
@Table(name = "person")
public class Person {

    @Id
    private int personId;
    private String personName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    public Person(){}

    public Person(int personId, String personName, Passport passport){
        this.personId = personId;
        this.personName = personName;
        this.passport = passport;
    }
    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public Passport getPassport() {
        return passport;
    }
    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}