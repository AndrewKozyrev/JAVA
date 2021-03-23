package ru.iteco.data;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
  private int order_id;
  private int employee_id;
  private int customer_id;
  private int car_id;
  private Date date_processed;
  private Date rent_start_date;
  private Date rent_end_date;
  private int days;
  private String rate_applied;
  private float order_total;
  private boolean order_status;
}
