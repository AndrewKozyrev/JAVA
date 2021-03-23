package main.java;

import java.time.LocalDate;

public class Manager extends Employee {

  public Manager (Company company) {
    this.company = company;
    salary = (int) (60 + 50 * Math.random()) * 1000;
  }

  @Override
  protected void calculateSalary() {
    if (lastIncome == null || lastIncome.plusMonths(1).isBefore(LocalDate.now())) {
      lastIncome = LocalDate.now();
      int earnedForCompany = (int) (115 + 25 * Math.random()) * 1000;
      int bonus = (int) (0.05 * earnedForCompany);
      totalSalary = salary + bonus;
    }
  }

}
