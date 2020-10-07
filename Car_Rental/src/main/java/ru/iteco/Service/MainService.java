package ru.iteco.Service;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.iteco.Model.Customer;
import ru.iteco.Model.Employee;
import ru.iteco.Model.Order;

import java.util.Map;

public class MainService {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

    Customer customer = (Customer) context.getBean("customer");
    Employee employee = (Employee) context.getBean("employee");
    Map<String, String> product_description = customer.getDescription();
    int car_id = customer.chooseVehicle(product_description);
    Order order = (Order) context.getBean("order");
    customer.makeOrder(car_id, 70, order);
    employee.calculateCost(order);
    employee.registerOrder(order);
}
