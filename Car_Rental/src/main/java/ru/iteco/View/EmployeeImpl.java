package ru.iteco.View;

import ru.iteco.DAO.CRUD;

import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeImpl {
    private int employee_id;
    private String first_name;
    private String last_name;

    public EmployeeImpl() {
        Map<String, String> map = getEmployee();
        this.employee_id = Integer.parseInt(map.get("employee_id"));
        this.first_name = map.get("first_name");
        this.last_name = map.get("last_name");
    }

    public EmployeeImpl(String first_name, String last_name) {
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

    public void calculateCost(OrderImpl order) {
        CarImpl car = new CarImpl(order.getCar_id());
        String SQL = "SELECT rate_id FROM rate WHERE category = " + "'" + car.getCategory(order.getCar_id()) + "'";
        var temp = (Map<String, ArrayList<String>>) CRUD.getDataByQuery(SQL);
        int rate_id = Integer.parseInt(temp.get("rate_id").get(0));
        RateImpl rate = new RateImpl(rate_id);

        int numberOfDays = order.getDays();
        int appliedRate;
        double total;

        if ( numberOfDays < 7 ) {
            appliedRate = rate.getDaily();
            order.setRate_applied("$" + appliedRate + "/day");
            total = appliedRate * numberOfDays;
        }
        else if (numberOfDays < 30) {
            appliedRate = rate.getWeekly();
            order.setRate_applied("$" + appliedRate + "/week");
            total = Math.round(numberOfDays / 7.0) * appliedRate;
        }
        else {
            appliedRate = rate.getMonthly();
            order.setRate_applied("$" + appliedRate + "/month");
            total = numberOfDays * appliedRate / 30.0;
        }

        order.setOrder_total(total);
    }

    public void registerOrder(OrderImpl order) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar rent_start_date = new GregorianCalendar();
        rent_start_date.add(Calendar.DAY_OF_MONTH, order.getDays());
        String rent_end_date = sdf.format(rent_start_date.getTime());
        order.setRent_end_date(rent_end_date);
        order.setOrder_status((byte) 1);

        Map<String, String> dataToInsert = new HashMap<>();
        dataToInsert.put("employee_id", Integer.toString(order.getEmployee_id()));
        dataToInsert.put("customer_id", Integer.toString(order.getCustomer_id()));
        dataToInsert.put("car_id", Integer.toString(order.getCar_id()));
        dataToInsert.put("rent_end_date", "'" + order.getRent_end_date() + "'");
        dataToInsert.put("days", Integer.toString(order.getDays()));
        dataToInsert.put("rate_applied", "'" + order.getRate_applied() + "'");
        dataToInsert.put("order_total", Double.toString(order.getOrder_total()));
        dataToInsert.put("order_status", Integer.toString(order.getOrder_status()));

        String receipt = CRUD.setData(order, dataToInsert);
        order.setOrder_id(Integer.parseInt(receipt));
    }

    public void checkOrder(OrderImpl order) {
        Map<String, String> result = CRUD.getData(order, order.getOrder_id());

        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
