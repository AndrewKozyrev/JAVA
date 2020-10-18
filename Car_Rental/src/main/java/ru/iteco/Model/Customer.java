package ru.iteco.Model;

import ru.iteco.DAO.CRUD;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private int customer_id;
    private String first_name;
    private String last_name;

    public Customer() {
        Map<String, String> map = getCustomer();
        this.customer_id = Integer.parseInt(map.get("customer_id"));
        this.first_name = map.get("first_name");
        this.last_name = map.get("last_name");
    }

    public Customer(String first_name, String last_name) {
        Map<String, String> map = createCustomer(first_name, last_name);
        this.customer_id = Integer.parseInt(map.get("customer_id"));
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Map<String, String> getDescription() {
        Car car = new Car(5);
        Map<String, String> map = car.getDescription();
        return map;
    }

    public Map<String, String> getCustomer() {
        return CRUD.getData(this);
    }

    public Map<String, String> createCustomer(String first_name, String last_name) {
        return new HashMap<>();
    }

    public int getCustomer_id() {
        return this.customer_id;
    }

    public int chooseVehicle(Map<String, String> car_description) {
        return Integer.parseInt(car_description.get("car_id"));
    }

    public void makeOrder(int car_id, int days, Order order)
    {
        order.setCar_id(car_id);
        order.setDays(days);
    }
}
