package net.serenitybdd.tutorials.screenplay.bankaccounts.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.tutorials.screenplay.bankaccounts.model.AccountStatus;

public class CurrentAccountStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new Cast());
    }

    @Given("{actor} has a current account with a balance of ${int} and a withdrawal limit of ${int}")
    public void hasACurrentAccount$(Actor actor, int initialBalance, int withdrawalLimit) {}

    @When("{actor} deposits or withdraws ${int}")
    public void depositOrWithdraw(Actor actor, int amount){}

    @Then("his/her total balance should be ${int}")
    public void totalBalanceShouldBe(int totalBalance) {
    }

    @And("his/her account status should be {}")
    public void accountStatusShouldBeStatus(AccountStatus accountStatus) {
    }
}
