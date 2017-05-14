package io.chrismajor.wlt.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Christo on 14/05/2017.
 */
@Entity
public class Person {

    private Long id;

    private String forename;

    private String surname;

    private Timestamp dob;

    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    @OneToOne(mappedBy = "person")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
