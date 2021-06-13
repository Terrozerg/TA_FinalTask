Feature: login
  As a registered user
  I want to be able to login
  So i can have access to registered user's features

  @login @capcha
  Scenario Outline: Login into account with valid credentials
    Given User opens home page
    And User checks 'Sign in' button visibility
    When User clicks 'Sign in' button
    And User checks Login field visibility
    And User enters login '<login>'
    And User clicks Continue button
    And User checks Password field visibility
    And User enters password '<password>'
    And User clicks password 'Sign in' button
    Then User should see welcome <message>

    Examples:
      | login              | password          | message               |
      | kot10601@zwoho.com | seleniumtestta123 | "Hi seleniumtest...!" |

  @login @capcha @negative
  Scenario Outline: Login into account with invalid login
    Given User opens home page
    And User checks 'Sign in' button visibility
    When User clicks 'Sign in' button
    And User checks Login field visibility
    And User enters login '<login>'
    And User clicks Continue button
    Then User should see error <message>

    Examples:
      | login  | message                     |
      | 321321 | "Oops, that's not a match." |

  @login @capcha @negative
  Scenario Outline: Login into account with invalid password
    Given User opens home page
    And User checks 'Sign in' button visibility
    When User clicks 'Sign in' button
    And User checks Login field visibility
    And User enters login '<login>'
    And User clicks Continue button
    And User checks Password field visibility
    And User enters password '<password>'
    And User clicks password 'Sign in' button
    Then User should see error <message>

    Examples:
      | login | password | message                     |
      | 1     | 543253   | "Oops, that s not a match." |