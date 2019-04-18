Feature: To update a customer

  Scenario Outline: client is updating a customer
    Given the customer with customer id is <id>
    When the customer after update has name is "<name>" and address is "<address>"
    And the client calls Put "api/customers" with the given details
    Then result status is "<status>"

    Examples: 
      | id | name      | address  | status  |
      | 10 | Rooney    | England  | SUCCESS |
      |  9 | Hong Son  | Ha Giang | SUCCESS |
      | 99 | Bich Hong | Ca Mau   | FAILED  |
