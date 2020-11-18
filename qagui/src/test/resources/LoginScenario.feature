@BDD
Feature: User login

  @login @wordpress
  Scenario: Login
    Given User start on main page
    When User logs to user panel
    Then User can modify user profile

  @login
  Scenario: Login2
    Given User start on main page
    When User logs to user panel
    And User check and click find button
    Then User go to search panel