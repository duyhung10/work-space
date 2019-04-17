Feature: To get the customer with customer details

  Scenario: get the customer with customer id
    Given client in localhost
    When the client calls GET "api/customers" with customer id as 54
    Then the status is "SUCCESS"
    And the response contains customer name "Hoang"