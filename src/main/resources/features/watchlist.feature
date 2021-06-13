Feature: watchlist
  As a signed in user
  I want to have watchlist
  So i can save viewed items

  @smoke @login
  Scenario Outline: Add item to watchlist
    Given User is signed into account
    And User opens item page '<ItemPage>'
    And User check item title visibility
    When User adds item to watchlist
    And User check Watchlist button visibility
    And User clicks Watchlist button
    Then Watchlist should contain the item

    Examples:
      | ItemPage                              |
      | https://www.ebay.com/itm/393182867855 |
      | https://www.ebay.com/itm/114725528054 |
      | https://www.ebay.com/itm/143727991859 |


