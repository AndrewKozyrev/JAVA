package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@IdClass(PurchaseList.Key.class)
@Table(name = "PurchaseList")
public class PurchaseList {
  @Id
  private String studentName;

  @Id
  private String courseName;

  private int price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  @EqualsAndHashCode
  public static class Key implements Serializable {
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;
  }
}
