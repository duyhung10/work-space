Feature: To delete a customer by customer id

  Scenario: delete the customer with customer id
    Given client delete customer with id is 10
    When the client calls Delete "api/customers"
    Then response status is "SUCCESS"
