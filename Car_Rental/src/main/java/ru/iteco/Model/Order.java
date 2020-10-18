package ru.iteco.Model;

public class Order {
    private int order_id;
    private int customer_id;
    private int car_id;
    private int employee_id;
    private int days;
    private String rent_end_date;
    private String rate_applied;
    private double order_total;
    private byte order_status;

    public Order(Customer customer, Employee employee) {
        this.customer_id = customer.getCustomer_id();
        this.employee_id = employee.getEmployee_id();
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getCar_id() {
        return this.car_id;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getRent_end_date() {
        return rent_end_date;
    }

    public String getRate_applied() {
        return rate_applied;
    }

    public double getOrder_total() {
        return order_total;
    }

    public byte getOrder_status() {
        return order_status;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public void setRent_end_date(String rent_end_date) {
        this.rent_end_date = rent_end_date;
    }

    public void setRate_applied(String rate_applied) {
        this.rate_applied = rate_applied;
    }

    public void setOrder_status(byte order_status) {
        this.order_status = order_status;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }
}
