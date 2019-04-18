Feature: To create new customer

  Scenario Outline: client create a new customer
    Given the customer with customer name "<name>" and customer address "<address>"
    When the client calls POST "api/customers" with the given details
    Then the client receives result status is "<status>"
    And response customer name "<nameResult>"
    And response customer address "<addressResult>"
		Examples:
		| name      | address   | status  | nameResult | addressResult |
		| Thanh Son | Bac Giang | SUCCESS | Thanh Son  | Bac Giang     |
		| Van Hung  | Lang Son  | SUCCESS | Van Hung   | Lang Son      |
