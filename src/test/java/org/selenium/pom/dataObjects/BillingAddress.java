package org.selenium.pom.dataObjects;

public class BillingAddress {
    private String firstName;
    private String lastName;
    private String addLine1;
    private String addLine2;
    private String city;
    private String postalCode;
    private String email;
    private String phone;
    private String companyName;
    private String country;
    private String state;

    public BillingAddress()
    {

    }

    public BillingAddress(String firstName, String lastName, String companyName, String country, String addLine1,
                          String addLine2, String city, String state, String postalCode, String phone, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.country = country;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public BillingAddress setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
        return this;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public BillingAddress setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public BillingAddress setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BillingAddress setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
    public String getCountry() {
        return country;
    }

    public BillingAddress setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public BillingAddress setState(String state) {
        this.state = state;
        return this;
    }

}
