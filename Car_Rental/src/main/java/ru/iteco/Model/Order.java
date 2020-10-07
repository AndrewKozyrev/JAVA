package ru.iteco.Model;

public class Order {
    private int customer_id;
    private int car_id;
    private int employee_id;
    private int days;
    private String rent_start_date;
    private String rate_applied;
    private double order_total;

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

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }
}
