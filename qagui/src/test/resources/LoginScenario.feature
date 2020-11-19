
@BDD
Feature: User login

  @login @wordpress @userpanel
  Scenario: Login
    Given User starts on main page
    When User logs to the user panel
    Then User can modify user profile

  @wordpress @userpanel @search
  Scenario: Login
    Given User starts on main page
    When User logs to the user panel
    And User checks search button
    When User clicks to search button
    Then User arrives to search panel

  @website
  Scenario: Check title of website
    Given website has got title
      | website | http://www.softpost.org                                |
      | title   | Software and Hardware reviews, Business ads, Tutorials |

  @website_2
  Scenario: Check title of the website
    Given Name of the website "http://www.softpost.org"
    Then Title is "Software and Hardware reviews, Business ads, Tutorials"

#  @website_3
#  Scenario Outline: Check title of the website
#    Given Name of the website is <website>
#    Then Title of this website is <title>
#    Examples:
#      | website                 | title                                                  |
#      | http://www.softpost.org | Software and Hardware reviews, Business ads, Tutorials |
#      | http://www.google.com   | google                                                 |