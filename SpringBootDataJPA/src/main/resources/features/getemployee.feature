Feature: To get all Employee

  Scenario: get all Employee
    Given the clien in localhost
    When the client click Show All Employee
    Then the client receives status code of 200
