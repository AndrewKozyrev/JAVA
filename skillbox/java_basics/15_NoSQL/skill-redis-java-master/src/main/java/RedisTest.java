import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class RedisTest {

  // На сайте зарегистрировалось 20 пользователей
  private static final int USERS = 20;

  // Также мы добавим задержку между показами
  private static final int SLEEP = 1000; // 1 секунда

  private static void log(String userName)
  {
    String log = String.format("— На главной странице показываем пользователя %s", userName);
    System.out.println(log);
  }

  // В одном из 10 случаев случайный пользователь оплачивает услугу
  private static void randomPayment(RedisStorage redis)
  {
    if (Math.random() < 0.1)
    {
      new Thread(() -> {
        int userId = new Random().nextInt(USERS);
        String user = redis.getUserByRank(userId);
        System.out.printf("> Пользователь %s оплатил платную услугу\n", user);
        redis.addUser(user, 0);
      }).start();
    }
  }

  public static void main(String[] args)
  {
    RedisStorage redis = new RedisStorage();
    Scanner scanner = new Scanner(System.in);
    redis.init();
    // Зарегистрируем 20 пользователей
    for (int i = 1; i <= 20; i++)
    {
      redis.addUser(String.valueOf(i), System.currentTimeMillis());
    }
    // Программа должна запускать бесконечный цикл
    Callable task = () -> {
      while (true)
      {
        String userName = redis.popUser();
        redis.addUser(userName, System.currentTimeMillis());
        log(userName);
        randomPayment(redis);
        Thread.sleep(SLEEP);
      }
    };
    var service = Executors.newSingleThreadExecutor();
    service.submit(task);
    while (true)
    {
      var line = scanner.nextLine();
      if (line.equals(""))
      {
        service.shutdown();
        System.out.println("FINISHED!");
        redis.shutdown();
        break;
      }
    }
  }
}
