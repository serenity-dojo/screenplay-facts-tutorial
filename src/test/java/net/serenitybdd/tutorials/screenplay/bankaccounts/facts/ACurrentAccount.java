package net.serenitybdd.tutorials.screenplay.bankaccounts.facts;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;
import net.serenitybdd.tutorials.screenplay.bankaccounts.CurrentAccountAPI;

public class ACurrentAccount implements Fact {
    private final int initialBalance;
    private int withdrawalLimit;
    CurrentAccountAPI accountsAPI = new CurrentAccountAPI();

    public ACurrentAccount(int initialBalance) {
        this.initialBalance = initialBalance;
    }

    public static ACurrentAccount withABalanceOf(int initialBalance) {
        return new ACurrentAccount(initialBalance);
    }

    public ACurrentAccount andAWithdrawalLimitOf(int withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
        return this;
    }

    @Override
    public void setup(Actor actor) {
        System.out.println("CREATE A NEW ACCOUNT");
        accountsAPI.openAccount(actor.getName(), initialBalance, withdrawalLimit);
    }

    @Override
    public void teardown(Actor actor) {
        System.out.println("DELETE THE ACCOUNT");
        accountsAPI.closeAccount(actor.getName());
    }
}
