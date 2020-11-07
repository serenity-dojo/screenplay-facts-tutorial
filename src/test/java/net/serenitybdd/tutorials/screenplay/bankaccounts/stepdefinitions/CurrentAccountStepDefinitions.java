package net.serenitybdd.tutorials.screenplay.bankaccounts.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.tutorials.screenplay.bankaccounts.CurrentAccountAPI;
import net.serenitybdd.tutorials.screenplay.bankaccounts.facts.ACurrentAccount;
import net.serenitybdd.tutorials.screenplay.bankaccounts.model.AccountStatus;
import net.serenitybdd.tutorials.screenplay.bankaccounts.model.CurrentAccount;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrentAccountStepDefinitions {

    CurrentAccountAPI accountAPI = new CurrentAccountAPI();

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new Cast());
    }

    @Given("{actor} has a current account with a balance of ${int} and a withdrawal limit of ${int}")
    public void hasACurrentAccount$(Actor actor, int initialBalance, int withdrawalLimit) {
        actor.has(
                ACurrentAccount.withABalanceOf(initialBalance).andAWithdrawalLimitOf(withdrawalLimit)
        );
    }

    @When("{actor} deposits or withdraws ${int}")
    public void depositOrWithdraw(Actor actor, int amount){
        accountAPI.deposit(actor.getName(), amount);
    }

    @Then("his/her total balance should be ${int}")
    public void totalBalanceShouldBe(int totalBalance) {
        CurrentAccount account = accountAPI.findAccountByOwner(theActorInTheSpotlight().getName());
        assertThat(account.getBalance()).isEqualTo(totalBalance);
    }

    @And("his/her account status should be {}")
    public void accountStatusShouldBeStatus(AccountStatus accountStatus) {
        CurrentAccount account = accountAPI.findAccountByOwner(theActorInTheSpotlight().getName());
        assertThat(account.getStatus()).isEqualTo(accountStatus);
    }

    @When("{actor} transfers ${int} to {actor}")
    public void kathyTransfersToKevin(Actor sender, int amount, Actor receiver) {
        accountAPI.transfer(sender.getName(), receiver.getName(), amount);

    }

    @Then("{actor} should have a balance of ${int}")
    public void kathyShouldHaveABalanceOf(Actor actor, int balance) {
        CurrentAccount account = accountAPI.findAccountByOwner(actor.getName());
        assertThat(account.getBalance()).isEqualTo(balance);
    }
}
