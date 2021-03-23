import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/skillbox";
    String user = "root";
    String pass = "b2e3t8a2";
    // запрос
    String query = "SELECT course_name, "
        + "ROUND(COUNT(*) / (MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date)) + 1), 3) avg "
        + "FROM PurchaseList "
        + "WHERE YEAR(subscription_date) = 2018 "
        + "GROUP BY course_name "
        + "ORDER BY avg DESC;";
    // создаём соединение к БД
    try (Connection con = DriverManager.getConnection(url, user, pass)) {
      // открываем переменную к запросам
      Statement statement = con.createStatement();
      // выполняем запрос
      var rs = statement.executeQuery(query);
      // получить данные о таблице
      var meta = rs.getMetaData();
      // вывести имена столбцов
      System.out.printf("%-50s%15s\n", meta.getColumnName(1), meta.getColumnName(2));
      System.out.println("=================================================================");
      // пока есть строки в таблице
      while (rs.next()) {
        // получить значение первого столбца
        String name = rs.getString(1);
        // получить значение второго столбца
        String value = rs.getString(2);
        System.out.printf("%-50s%15s\n", name, value);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

}
