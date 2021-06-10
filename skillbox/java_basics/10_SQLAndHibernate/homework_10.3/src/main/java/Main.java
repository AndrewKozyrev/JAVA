import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

  public static void main(String[] args) {
    var url = "jdbc:mysql://localhost:3306/skillbox";
    var user = "root";
    var pass = "b2e3t8a2";
    var query = "INSERT INTO LinkedPurchaseList "
        + "SELECT A.id student_id, B.id course_id FROM Students A "
        + "JOIN PurchaseList C ON A.name = C.student_name "
        + "JOIN Courses B ON C.course_name = B.name";

    try (var con = DriverManager.getConnection(url, user, pass)) {
      var statement = con.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    var registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();
    var metadata = new MetadataSources(registry).getMetadataBuilder().build();
    var sessionFactory = metadata.getSessionFactoryBuilder().build();

    var session = sessionFactory.openSession();

    var list = session.createQuery("FROM LinkedPurchaseList").getResultList();
    list.forEach(System.out::println);

    session.close();
  }

}
