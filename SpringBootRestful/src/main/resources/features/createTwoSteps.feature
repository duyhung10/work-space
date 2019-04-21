Feature: To create new customer with two steps

  Scenario Outline: client create a new customer
    Given have customer name "<name>" and customer address "<address>"
    When calls POST "api/customers-two-steps" with the given details
    Then status is "<status>"

    Examples: 
      | name  | address   | status  |
      | Pedro | Bac Giang | SUCCESS |
      | Alex  | Default   | FAILED  |
