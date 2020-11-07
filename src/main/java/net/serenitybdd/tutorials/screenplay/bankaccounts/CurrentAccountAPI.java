package net.serenitybdd.tutorials.screenplay.bankaccounts;

import net.serenitybdd.tutorials.screenplay.bankaccounts.model.CurrentAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CurrentAccountAPI {
    private static Map<String, CurrentAccount> accounts = new HashMap<>();
    private static AtomicInteger numberCounter = new AtomicInteger();

    public CurrentAccount openAccount(String owner, int initialBalance, int withdrawalLimit) {
        CurrentAccount account = new CurrentAccount(numberCounter.incrementAndGet(), owner, initialBalance, withdrawalLimit);
        accounts.put(owner, account);
        printAccountStatus();
        return account;
    }

    public void closeAccount(String owner) {
        accounts.remove(owner);
    }

    public CurrentAccount findAccountByOwner(String name) {
        return accounts.get(name);
    }

    public CurrentAccount deposit(String owner, int amount) {
        if (amount < 0) {
            return withdraw(owner, Math.abs(amount));
        } else {
            CurrentAccount account = accounts.get(owner);
            account.deposit(amount);
            printAccountStatus();
            return account;
        }
    }

    public CurrentAccount withdraw(String owner, int amount) {
        CurrentAccount account = accounts.get(owner);
        if ((amount - account.getBalance() ) > account.getWithdrawalLimit()) {
            account.withdraw(10);
        } else if (amount > account.getBalance()) {
            account.withdraw(amount);
            account.withdraw(5);
        } else {
            account.withdraw(amount);
        }
        printAccountStatus();
        return account;
    }

    private void printAccountStatus() {
        accounts.values().forEach(
                System.out::println
        );
    }

    public void transfer(String sender, String receiver, int amount) {
        accounts.get(sender).deposit(-1 * amount);
        accounts.get(receiver).deposit(amount);
        printAccountStatus();
    }
}
