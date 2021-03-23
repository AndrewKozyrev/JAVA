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
@IdClass(Subscription.SubscriptionKey.class)
@Table(name = "Subscriptions")
public class Subscription {
  @Id
  private int studentId;

  @Id
  private int courseId;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public static class SubscriptionKey implements Serializable {
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;
  }
}
