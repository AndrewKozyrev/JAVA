package ru.iteco.Service;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.iteco.View.CustomerImpl;
import ru.iteco.View.EmployeeImpl;
import ru.iteco.View.OrderImpl;


import java.util.Map;

public class MainService {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        CustomerImpl customerImpl = (CustomerImpl) context.getBean("customer");
        EmployeeImpl employeeImpl = (EmployeeImpl) context.getBean("employee");
        Map<String, String> product_description = customerImpl.getDescription();
        int car_id = customerImpl.chooseVehicle(product_description);
        OrderImpl order = (OrderImpl) context.getBean("order");
        customerImpl.makeOrder(car_id, 30, order);
        employeeImpl.calculateCost(order);
        employeeImpl.registerOrder(order);
        employeeImpl.checkOrder(order);
    }
}
