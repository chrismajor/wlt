package io.chrismajor.wlt.ui.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * UI bean to describe a user's details
 */
public class UserDetails {
    /** username / email address*/
    @NotNull(message = "Please enter an email address")
    @Size(min = 1, max = 255, message = "Please enter an email address")
    @Email(message = "Please enter a valid email address")
    private String username;

    /** password */
    @NotNull(message = "Please enter a password")
    @Size(min = 1, max = 255, message = "Please enter a password")
    private String password;

    /** password confirmation (has to match password field) */
    @NotNull(message = "Please confirm your password")
    @Size(min = 1, max = 255, message = "Please confirm your password")
    private String passwordConfirm;

    /** first name */
    @NotNull(message = "Please enter your forename")
    @Size(min = 1, max = 255, message = "Please enter your forename")
    private String forename;

    /** last name */
    @NotNull(message = "Please enter your surname")
    @Size(min = 1, max = 255, message = "Please enter your surname")
    private String surname;

    /** date of birth */
    @NotNull(message = "Please enter your date of birth")
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private Date dob;

    /** first line of user's address */
    @NotNull(message = "Please enter the first line of your address")
    @Size(min = 1, max = 255, message = "Please enter the first line of your address")
    private String addressLine1;

    /** second line of user's address (optional) */
    @Size(max = 255, message = "That's too much address")
    private String addressLine2;

    /** town / city */
    @NotNull(message = "Please enter your town / city")
    @Size(min = 1, max = 255, message = "Please enter your town / city")
    private String town;

    /** county / state */
    @NotNull(message = "Please enter your county")
    @Size(min = 1, max = 255, message = "Please enter your county")
    private String county;

    /** country */
    @NotNull(message = "Please enter your country")
    @Size(min = 1, max = 255, message = "Please enter your country")
    private String country;

    /** post code / zip code */
    @NotNull(message = "Please enter your postcode")
    @Size(min = 1, max = 15, message = "Please enter your postcode")
    private String postcode;

    /** validation rule to ensure the password matches the password confirmation field */
    @AssertTrue(message="Passwords don't match")
    private boolean isValid() {
        return password.equals(passwordConfirm);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
