package io.chrismajor.wlt.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(forename, person.forename) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(dob, person.dob) &&
                Objects.equals(user, person.user) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forename, surname, dob, user, address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", address=" + address +
                '}';
    }
}
