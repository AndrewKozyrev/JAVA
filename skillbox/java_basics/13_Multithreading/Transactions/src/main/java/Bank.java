import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Bank {
    @Getter
    @Setter
    private Map<String, Account> accounts;
    private final Random random = new Random();
    private Semaphore semaphore = new Semaphore(100);

    public Bank(Collection<Account> accounts) {
        this.accounts = new HashMap<>();
        for (var acc : accounts) {
            var accNumber = acc.getAccNumber();
            this.accounts.put(accNumber, acc);
        }
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        if (!isLegit(fromAccountNum, toAccountNum, amount)) {
            return;
        }
        var from = accounts.get(fromAccountNum);
        var to = accounts.get(toAccountNum);
        // остановить поток пока не завершится метод getSumAllAccounts
        semaphore.acquire();
        synchronized (from.compareTo(to) < 0 ? from : to) {
            synchronized (from.compareTo(to) < 0 ? to : from) {
                if (from.getMoney() < amount) {
                    throw new InsufficientFundsException();
                }
                else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }
        }
        semaphore.release();
    }

    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        // остановить все потоки
        semaphore.drainPermits();

        var value = accounts.keySet().stream()
            .map(this::getBalance)
            .reduce(Long::sum)
            .get();
        semaphore.release(100);
        return value;
    }

    public void blockAccount(String accountNum) {
        accounts.get(accountNum).setBlocked(true);
    }

    private boolean isBlocked(String accountNum) {
        return accounts.get(accountNum).isBlocked();
    }

    private boolean isLegit(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        boolean sameAccounts = fromAccountNum.equals(toAccountNum);
        boolean blockedAccount = isBlocked(fromAccountNum) || isBlocked(toAccountNum);

        if (sameAccounts || blockedAccount) {
            return false;
        }

        if (amount > 50_000L) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                blockAccount(fromAccountNum);
                blockAccount(toAccountNum);
                return false;
            }
        }
        return true;
    }
}
