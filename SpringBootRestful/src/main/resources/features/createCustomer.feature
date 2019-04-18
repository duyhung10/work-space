Feature: To create new customer

  Scenario: client create a new customer
    Given the customer with customer name "Ronaldo" and customer address "Nam Dinh, Ha Noi"
    When the client calls POST "api/customers" with the given details
    Then the client receives result status is "SUCCESS"
    And response customer name "Ronaldo"
    And response customer address "Nam Dinh, Ha Noi"
