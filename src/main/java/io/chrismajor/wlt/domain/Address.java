package io.chrismajor.wlt.domain;

import javax.persistence.*;

/**
 * Entity bean for an address
 */
@Entity
public class Address {

    /** primary key */
    private Long id;

    /** first line of the address */
    private String addressLine1;

    /** second line of the address */
    private String addressLine2;

    /** town or city */
    private String town;

    /** county or state */
    private String county;

    /** country */
    private String country;

    /** post / zip code */
    private String postCode;

    /** person details */
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "address_line_1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Column(name = "address_line_2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @OneToOne(mappedBy = "address")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
