package io.chrismajor.wlt.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Entity class to describe a person
 */
@Entity
public class Person {

    /** primary key */
    private Long id;

    /** the first name */
    private String forename;

    /** the last name */
    private String surname;

    /** date of birth */
    private Timestamp dob;

    /** the person's user account (if applicable) */
    private User user;

    /** the person's address */
    private Address address;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
