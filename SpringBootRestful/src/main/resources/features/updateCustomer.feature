Feature: To update a customer

  Scenario: client is updating a customer
    Given the customer with customer id is 10
    When the customer after update has name is "Ronaldo" and address is "Portugal"
    And the client calls Put "api/customers" with the given details
    Then result status is "SUCCESS"
    And result customer name "Ronaldo"
    And result customer address "Portugal"
