Feature: Login feature

  Scenario: Login Scenario
    Given I open Login Page
    And I open Login Page
    When I enter email "gisel.montano-patino@testpro.io"
    And I enter password "TestPro123"
    And I submit
    Then I am logged in

  Scenario Outline: Negative Login Scenario
    Given I open Login Page
    And I open Login Page
    When I enter email "<email>"
    And I enter password "<password>"
    And I submit
    Then I should not get logged in
    Examples:
    |email                            |password   |
    |gisel.montano-patino@testpro.io  |TestPro123 |
    |valid@testpro.io                 |invalidpass|
    |testing@testpro.io               |    |
    |                                 |tespass    |