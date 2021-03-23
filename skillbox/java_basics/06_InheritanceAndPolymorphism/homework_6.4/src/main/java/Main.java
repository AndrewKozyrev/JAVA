package main.java;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Company company1 = new Company(1000000);
    List<Employee> employeeList = new ArrayList<>(270);

    for (int i = 0; i < 270; i++) {
      if (i < 180) {
        employeeList.add(new Operator(company1));
      }
      else if ( i < 260) {
        employeeList.add(new Manager(company1));
      } else {
        employeeList.add(new TopManager(company1));
      }
    }

    company1.hireAll(employeeList);

    System.out.println("TOP 15\n======================");
    for (Employee e : company1.getTopSalaryStaff(15)) {
      System.out.println(e.getMonthSalary() + " руб.");
    }

    System.out.println("\nBOTTOM 30\n======================");
    for (Employee e : company1.getLowestSalaryStaff(30)) {
      System.out.println(e.getMonthSalary() + " руб.");
    }

    System.out.println("\nУвольняем 50%\n======================");
    company1.fire(0.5);

    System.out.println("TOP 15\n======================");
    for (Employee e : company1.getTopSalaryStaff(15)) {
      System.out.println(e.getMonthSalary() + " руб.");
    }

    System.out.println("\nBOTTOM 30\n======================");
    for (Employee e : company1.getLowestSalaryStaff(30)) {
      System.out.println(e.getMonthSalary() + " руб.");
    }

  }

}
