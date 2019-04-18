Feature: To get the customer with customer details

  Scenario Outline: get the customer with customer id
    Given client in localhost
    When the client calls GET "api/customers" with customer id as <id>
    Then the status is "<status>"
    And the response contains customer name "<name>"
    Examples: 
      | id | status  | name         |
      | 2  | SUCCESS | Nguyen Hoang |
      | 4  | SUCCESS | Son          |
      | 8  | FAILED  | null         |
