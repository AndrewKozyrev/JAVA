package ru.iteco.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Rate {

  private int rate_id;
  private Category category;
  private float daily;
  private float weekly;
  private float monthly;
}
