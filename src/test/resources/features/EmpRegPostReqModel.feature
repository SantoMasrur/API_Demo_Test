Feature: Employee registration

  @test

  Scenario Outline: check employee registration successfully or not
    Given employee has the api '<path>'
    When employee hit '<name>' and '<job>'
    And call the api with body
    Then it will return created date data

    Examples:
    | path     | name  | job |
    | employee | santo | QA  |