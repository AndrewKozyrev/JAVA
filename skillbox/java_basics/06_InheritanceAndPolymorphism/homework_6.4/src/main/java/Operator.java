package main.java;

import java.time.LocalDate;

public class Operator extends Employee {

  public Operator (Company company) {
    this.company = company;
    salary = (int) (50 + 20 * Math.random()) * 1000;
  }

  @Override
  protected void calculateSalary() {
    if (lastIncome == null || lastIncome.plusMonths(1).isBefore(LocalDate.now())) {
      lastIncome = LocalDate.now();
      totalSalary = salary;
    }
  }

}
