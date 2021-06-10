import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Account implements Comparable<Account> {

    @NonNull
    private long money;
    @NonNull
    private String accNumber;
    private boolean blocked = false;

    public void debit(long amount) {
        money -= amount;
    }

    public void credit(long amount) {
        money += amount;
    }

    public synchronized void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public int compareTo(Account o) {
        return accNumber.compareTo(o.getAccNumber());
    }
}
