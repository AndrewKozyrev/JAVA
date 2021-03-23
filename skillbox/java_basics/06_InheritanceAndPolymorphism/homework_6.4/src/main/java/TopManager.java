package main.java;

import java.time.LocalDate;

public class TopManager extends Employee {

  public TopManager (Company company) {
    this.company = company;
    salary = (int) (60 + 100 * Math.random()) * 1000;
  }

  @Override
  protected void calculateSalary() {
    // Пусть зарплаты расчитываются только один раз за расчётный период
    if (lastIncome == null || lastIncome.plusMonths(1).isBefore(LocalDate.now())) {
      lastIncome = LocalDate.now();
      int bonus = 0;
      if (company.getIncome() > 10000000) {
        bonus = (int) (1.5 * salary);
      }
      totalSalary = salary + bonus;
    }
  }

}
