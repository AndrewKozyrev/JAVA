import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

import com.mongodb.MongoClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.bson.Document;

public class Main {

  public static void main(String[] args) {
    // Подключаемся к серверу MongoDB
    var client = new MongoClient("127.0.0.1" , 27017);
    var database = client.getDatabase("local");

    // Создаём коллекцию
    var students = database.getCollection("Students");

    // Удалим из нее все документы
    students.drop();

    List<String> lines = new ArrayList<>();
    try {
      // Читаем строки из файла
      lines = Files.readAllLines(Paths.get("src/main/resources/mongo.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Создаём список документов
    ArrayList<Document> documents = new ArrayList<>();
    lines.stream().map(line -> line.split(",", 3))
        .forEach(item -> {
          var doc = new Document();
          // Добавляем
          doc.append("name", item[0]) // имя
             .append("age", Integer.parseInt(item[1])); // воздраст
          var courses = item[2].replace("\"", "").split(",");
          doc.append("courses", Arrays.asList(courses)); // список курсов
          documents.add(doc);  // кладём в список документов
        });

    students.insertMany(documents);   // вставляем список документов в коллекцию
    System.out.println("— общее количество студентов в базе: " + students.countDocuments());

    System.out.println("— количество студентов старше 40 лет:");
    students.find(gt("age", 40)) // Находим студентов старше 40 лет
        .forEach((Consumer<Document>) System.out::println);

    System.out.println("— имя самого молодого студента: ");
    System.out.println(students.find()
        .sort(orderBy(ascending("age")))   // Сортируем по возрастанию
        .first()      // Выбираем первого студента из коллекции
        .get("name"));    // Берем его имя

    System.out.println("— список курсов самого старого студента:");
    var courses = students.find()
        .sort(orderBy(descending("age")))  // Сортируем по убыванию
        .first()  // Выбираем первого студента из коллекции
        .get("courses");  // Берем его список курсов
    System.out.println(courses);
  }

}
