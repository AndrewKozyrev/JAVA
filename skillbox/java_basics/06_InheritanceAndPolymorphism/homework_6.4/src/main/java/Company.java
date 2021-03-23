package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Company {
  private List<Employee> employees;
  private double income;

  public Company(double income) {
    employees = new ArrayList<>();
    this.income = income;
  }

  public Company(double income, List<Employee> list) {
    this(income);
    employees.addAll(list);
  }

  public void hire(Employee employee) {
    employees.add(employee);
  }

  public void hireAll(List<Employee> toHire) {
    employees.addAll(toHire);
  }

  public void fire(Employee employee) {
    employees.remove(employee);
  }

  public void fire(List<Employee> toFire) {
    employees.removeAll(toFire);
  }

  public void fire(int count) {
    if (count > employees.size() || count <= 0) {
      throw new IllegalArgumentException("Can't fire this amount.");
    }
    for (int i = 0; i < count; i++) {
      int index = new Random().nextInt(employees.size());
      employees.remove(index);
    }
  }

  public void fire(double fraction) {
    int count = (int) Math.floor(fraction * employees.size());
    fire(count);
  }

  public double getIncome() {
    return income;
  }

  public List<Employee> getTopSalaryStaff(int count) {
    if (count > employees.size() || count <= 0) {
      throw new IllegalArgumentException("Can't list.");
    }

    List<Employee> result = new ArrayList<>(count);

    Collections.sort(employees, Collections.reverseOrder());
    for (int i = 0; i < count; i++) {
      result.add(employees.get(i));
    }

    return result;
  }

  public List<Employee> getLowestSalaryStaff(int count) {
    if (count > employees.size() || count <= 0) {
      throw new IllegalArgumentException("Can't list.");
    }

    List<Employee> result = new ArrayList<>(count);

    Collections.sort(employees);
    for (int i = 0; i < count; i++) {
      result.add(employees.get(i));
    }

    return result;
  }

}
