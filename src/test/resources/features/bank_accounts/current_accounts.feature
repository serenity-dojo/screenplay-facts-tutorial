Feature: Current accounts

  Scenario Outline: Depositing and withdrawing
    Given Karen has a current account with a balance of $<Initial Balance> and a withdrawal limit of $100
    When she deposits or withdraws $<Deposit or Withdrawl>
    Then her total balance should be $<New Balance>
    And her account status should be <Status>

    Examples:
      | Initial Balance | Deposit or Withdrawl | New Balance | Status |
      | 0               | 1000                 | 1000        | Normal |
      | 1000            | -200                 | 800         | Normal |
    Examples: There is a $5 transaction fee if the account becomes overdrawn
      | Initial Balance | Deposit or Withdrawl | New Balance | Status    |
      | 200             | -300                 | -105        | Overdrawn |
    Examples: There is a $10 transaction fee when trying to withdraw more than the limit
      | Initial Balance | Deposit or Withdrawl | New Balance | Status |
      | 200             | -400                 | 190         | Normal |
