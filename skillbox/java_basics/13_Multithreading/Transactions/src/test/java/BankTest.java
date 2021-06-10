import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import junit.framework.TestCase;

public class BankTest extends TestCase {

  private static Bank bank;
  private static List<Account> accounts;
  private static final Random RAND = new Random();
  private static final int ACCOUNTS_COUNT = 10000;
  private static final int THREADS_COUNT = 20;

  @Override
  protected void setUp() throws Exception {
    System.out.println("START.");
    accounts = generateAccounts(ACCOUNTS_COUNT);
    bank = new Bank(accounts);
  }

  public ArrayList<Account> generateAccounts(int number) {
    var list = new ArrayList<Account>(number);
    for (int i = 0; i < number; i++) {
      long money = 10000000 + 1_000_000L * RAND.nextInt(999999999);
      String accNum = IntStream.range(0, 15)
          .map(x -> RAND.nextInt(10))
          .mapToObj(String::valueOf)
          .collect(Collectors.joining(""));
      list.add(new Account(money, accNum));
    }
    return list;
  }

  // тест на целостность операций по переводу платежей
  public void testTransfer() {
    var expected = bank.getSumAllAccounts();
    var testDuration = 10_000L;

    runThreads(THREADS_COUNT, testDuration, () -> {
      var from = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var to = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var sum = RAND.nextInt(100000);
      try {
        bank.transfer(from.getAccNumber(), to.getAccNumber(), sum);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    runThreads(THREADS_COUNT, testDuration, () -> {
      var acc =  accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      bank.getBalance(acc.getAccNumber());
    });

    waitFor(testDuration);
    var actual = bank.getSumAllAccounts();
    assertEquals(expected, actual);

  }

  // тест на целостность операций с одним счётом
  public void testTransaction() {
    var account1 = accounts.get(0).getAccNumber();
    var expected = bank.getBalance(account1);

    // сто потоков, каждый из них делает 100 переводов с одного счета
    for (int i = 0; i < 100; i++) {
      new Thread(() -> {
        for (int j = 0; j < 100000; j++) {
          var to = accounts.get(1 + RAND.nextInt(ACCOUNTS_COUNT - 1));
          var sum = 1;
          try {
            bank.transfer(account1, to.getAccNumber(), sum);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }

    // сто потоков, каждый из них делает 100 переводов на один и тот же счет
    for (int i = 0; i < 100; i++) {
      new Thread(() -> {
        for (int j = 0; j < 100000; j++) {
          var from = accounts.get(1 + RAND.nextInt(ACCOUNTS_COUNT - 1));
          var sum = 1;
          try {
            bank.transfer(from.getAccNumber(), account1, sum);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }

    waitFor(100_000L);
    var actual = bank.getBalance(account1);
    assertEquals(expected, actual);

  }

  // тест на проверку блокировки аккаунтов
  public void testBlockAccount() {
    var account1 = accounts.get(1);
    bank.blockAccount(account1.getAccNumber());
    var expected = bank.getBalance(account1.getAccNumber());
    var testDuration = 10_000L;

    runThreads(THREADS_COUNT, testDuration, () -> {
      var to = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var sum = RAND.nextInt(100000);
      try {
        bank.transfer(account1.getAccNumber(), to.getAccNumber(), sum);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    runThreads(THREADS_COUNT, testDuration, () -> {
      var from = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var sum = RAND.nextInt(100000);
      try {
        bank.transfer(from.getAccNumber(), account1.getAccNumber(), sum);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    waitFor(testDuration);
    var actual = bank.getBalance(account1.getAccNumber());
    assertEquals(expected, actual);

  }

  // тест на проверку работы метода getSumAllAccounts()
  public void testGetSumAllAccounts() {
    var expected = bank.getSumAllAccounts();
    var testDuration = 10_000L;
    var endTime = System.currentTimeMillis() + testDuration;

    runThreads(THREADS_COUNT, testDuration, () -> {
      var from = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var to = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var sum = RAND.nextInt(100000);
      try {
        bank.transfer(from.getAccNumber(), to.getAccNumber(), sum);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    while (System.currentTimeMillis() <= endTime) {
      var actual = bank.getSumAllAccounts();
      assertEquals(expected, actual);
    }

  }

  // тест метода Bank::isFraud; все аккаунты со временем заблокируются
  public void testIsFraud() {
    var testDuration = 50_000L;

    runThreads(THREADS_COUNT, testDuration, () -> {
      var from = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var to = accounts.get(RAND.nextInt(ACCOUNTS_COUNT));
      var sum = 50001;
      try {
        bank.transfer(from.getAccNumber(), to.getAccNumber(), sum);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    waitFor(testDuration);
    var blockedCount = accounts.stream().filter(Account::isBlocked).count();
    System.out.printf("%d из %d заблокировано за %d секунд\n",
        blockedCount, ACCOUNTS_COUNT, testDuration / 1000);
  }

  // декоратор для потоков
  public void runThreads(int count, long duration, Runnable task) {
    var endTime = System.currentTimeMillis() + duration;

    for (int i = 0; i < count; i++) {
      new Thread(() -> {
        while (System.currentTimeMillis() <= endTime) {
          task.run();
        }
      }).start();
    }
  }

  public void waitFor(long duration) {
    try {
      Thread.sleep(duration + 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void tearDown() throws Exception {
    accounts.clear();
    System.out.println("FINISH.");
  }
}
