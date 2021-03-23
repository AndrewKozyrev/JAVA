package ru.iteco.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
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

}
