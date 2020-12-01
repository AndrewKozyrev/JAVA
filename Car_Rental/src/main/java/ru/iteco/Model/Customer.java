package ru.iteco.Model;

public class Customer {
    private int customer_id;
    private String first_name;
    private String last_name;
    private String phone;
    private String address;
    private String zip_code;
    private String license_number;
    private String country;
    private String city;

    public int getCustomer_id() {
        return customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getLicense_number() {
        return license_number;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer() {

    }

    public Customer(int customer_id, String first_name, String last_name,
                    String phone, String address, String zip_code,
                    String license_number, String country, String city) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.zip_code = zip_code;
        this.license_number = license_number;
        this.country = country;
        this.city = city;
    }
}
