package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TODO
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String description;
  private String timestamp;
}
