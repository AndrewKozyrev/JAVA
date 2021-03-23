import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

  public static void main(String[] args) {
    var registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();
    var metadata = new MetadataSources(registry).getMetadataBuilder().build();
    var sessionFactory = metadata.getSessionFactoryBuilder().build();

    var session = sessionFactory.openSession();

    var builder = session.getCriteriaBuilder();
    var query = builder.createQuery(Course.class);
    var root = query.from(Course.class);
    query.select(root);
    var p = session.createQuery(query).getResultList();
    p.forEach(System.out::println);

    sessionFactory.close();
  }

}
