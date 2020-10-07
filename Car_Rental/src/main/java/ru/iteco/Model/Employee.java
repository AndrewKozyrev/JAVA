package ru.iteco.Model;

import ru.iteco.DAO.CRUD;

import java.util.Map;

public class Employee {
    private int employee_id;
    private String first_name;
    private String last_name;

    public Employee() {
        Map<String, String> map = getEmployee();
        this.employee_id = Integer.parseInt(map.get("employee_id"));
        this.first_name = map.get("first_name");
        this.last_name = map.get("last_name");
    }

    public Employee(String first_name, String last_name) {
        Map<String, String> map = createEmployee(first_name, last_name);
        this.employee_id = Integer.parseInt(map.get("employee_id"));
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Map<String, String> getEmployee() {
        return CRUD.getData(this);
    }

    public Map<String, String> createEmployee(String first_name, String last_name) {
        return null;
    }

    public int getEmployee_id() {
        return this.employee_id;
    }

    public void calculateCost(Order order) {
        Car car = new Car(order.getCar_id());
        String SQL = "SELECT rate_id FROM rate WHERE category = " + car.getCategory(order.getCar_id());
        int rate_id = (Integer) CRUD.getDataByQuery(SQL);
        Rate rate = new Rate(rate_id);

        int numberOfDays = order.getDays();
        int appliedRate;
        double total;

        if ( numberOfDays < 7 ) {
            appliedRate = rate.getDaily();
            total = appliedRate * numberOfDays;
        }
        else if (numberOfDays < 30) {
            appliedRate = rate.getWeekly();
            total = Math.round(numberOfDays / 7) * appliedRate;
        }
        else {
            appliedRate = rate.getMonthly();
            total = Math.round(numberOfDays / 30) * appliedRate;
        }

        order.setOrder_total(total);
    }
}
