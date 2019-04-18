Feature: To delete a customer by customer id

  Scenario Outline: delete the customer with customer id
    Given client delete customer with id is <id>
    When the client calls Delete "api/customers"
    Then response status is "<status>"

    Examples: 
      | id  | status  |
      |   1 | SUCCESS |
      | 112 | FAILED  |
      | 245 | FAILED  |
      |  12 | SUCCESS |
