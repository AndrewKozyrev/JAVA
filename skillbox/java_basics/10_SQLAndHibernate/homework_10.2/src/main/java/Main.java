import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

  public static void main(String[] args) {
    var registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();
    var metadata = new MetadataSources(registry).getMetadataBuilder().build();
    var sessionFactory = metadata.getSessionFactoryBuilder().build();

    var session = sessionFactory.openSession();

    Course course = session.get(Course.class, 2);
    System.out.println(course.getName() + " - " + course.getStudentsCount());

    sessionFactory.close();
  }
}
