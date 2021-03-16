package ru.iteco.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {

  private int employee_id;
  private int employee_number;
  private String first_name;
  private String last_name;
  private float hourly_salary;
}
