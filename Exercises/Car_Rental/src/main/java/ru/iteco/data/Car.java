package ru.iteco.data;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Car {
  private int car_id;
  private String tag_number;
  private String model;
  private String make;
  private Date car_year;
  private Category category;
  private boolean mp3_layer;
  private boolean dvd_player;
  private boolean air_conditioner;
  private boolean navigation;
  private boolean available;

}
