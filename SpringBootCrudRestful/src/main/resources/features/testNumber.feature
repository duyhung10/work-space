Feature: FizzBuzz Game play
  Scenario: Play FizzBuzz to get Fizz
    Given Create a FizzBuzz Game play
    When I play 21
    Then The result is "Fizz"
  
  Scenario: Play FizzBuzz to get Buzz
    Given Create a FizzBuzz Game play
    When I play 25
    Then The result is "Buzz"
