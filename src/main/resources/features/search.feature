Feature: search
  As a user
  I want to have search
  So i can search for an item

  @smoke
  Scenario Outline: Search for an item
    Given User opens home page
    And User checks search field visibility
    When User inputs '<keyword>' in search field
    And User clicks 'Search' button
    And User checks items listing visibility
    Then User checks items listing contains '<keyword>'

    Examples:
      | keyword |
      | Mirror  |
      | macbook |
      | antique |

  @smoke @negative
  Scenario Outline: Search for an non-existing item
    Given User opens home page
    And User checks search field visibility
    When User inputs '<keyword>' in search field
    And User clicks 'Search' button
    Then User should see search result <message>

    Examples:
      | keyword | message                |
      | j       | "Let's try that again" |
      | e       | "Let's try that again" |
      | 1       | "Let's try that again" |

    @smoke
    Scenario: Advanced search
      Given User opens home page
      And User check 'Advanced search' button visibility
      When User clicks 'Advanced search' button
      Then User check advanced search fields visibility