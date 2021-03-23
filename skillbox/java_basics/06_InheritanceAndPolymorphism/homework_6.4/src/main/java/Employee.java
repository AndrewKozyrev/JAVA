package main.java;

import java.time.LocalDate;

public abstract class Employee implements Comparable<Employee> {
  // Ссылка на компанию, где работает сотрудник
  protected Company company;
  // Фиксирования часть зарплаты
  protected int salary;
  // Полная зарплата за месяц
  protected int totalSalary;
  // Дата последней зарплаты
  protected LocalDate lastIncome;

  public int getMonthSalary() {
    calculateSalary();
    return totalSalary;
  }
  // Расчитывает зарплату раз в расчётный период
  abstract void calculateSalary();

  // Для упорядочивания сотрудников
  @Override
  public int compareTo(Employee o) {
    return Double.compare(this.getMonthSalary(), o.getMonthSalary());
  }
}
