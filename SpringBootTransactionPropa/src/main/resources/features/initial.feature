Feature: Test insert new person

  Scenario: Test insert new person initial
    Given Create  a new person 
    When I check Is person present 
    Then I check Is person's wallet exist 
    Then I check Is person's wallet amount 
