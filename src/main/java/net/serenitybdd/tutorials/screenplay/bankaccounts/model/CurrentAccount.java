package net.serenitybdd.tutorials.screenplay.bankaccounts.model;

public class CurrentAccount {
    private int number;
    private String owner;
    private int balance;
    private int withdrawalLimit;
    private AccountStatus status;

    public CurrentAccount(int number, String owner, int balance, int withdrawalLimit) {
        this.number = number;
        this.owner = owner;
        this.balance = balance;
        this.withdrawalLimit = withdrawalLimit;
        this.status = AccountStatus.Normal;
    }

    public int getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void deposit(int amount) {
        balance = balance + amount;
        status = (balance < 0) ? AccountStatus.Overdrawn : AccountStatus.Normal;
    }

    public void withdraw(int amount) {
        balance = balance - amount;
        status = (balance < 0) ? AccountStatus.Overdrawn : AccountStatus.Normal;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "number=" + number +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", withdrawalLimit=" + withdrawalLimit +
                ", status=" + status +
                '}';
    }
}
