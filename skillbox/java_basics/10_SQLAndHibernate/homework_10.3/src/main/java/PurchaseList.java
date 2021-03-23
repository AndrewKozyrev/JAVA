import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@IdClass(PurchaseList.PurchaseListKey.class)
@Table(name = "PurchaseList")
public class PurchaseList {
  @Id
  private String studentName;

  @Id
  private String courseName;

  private int price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public static class PurchaseListKey implements Serializable {
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;
  }
}
