package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@IdClass(LinkedPurchaseList.Key.class)
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {
  @Id
  private Integer studentId;

  @Id
  private Integer courseId;

  @EqualsAndHashCode
  public static class Key implements Serializable {
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;
  }
}
