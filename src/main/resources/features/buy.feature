Feature: buy
  As a user
  I want a buy feature
  So i can purchase items

  @capcha @retry
  Scenario Outline: Purchase an item as guest
    Given User opens item page '<ItemPage>'
    And User check item title visibility
    When User clicks 'Buy it now' button
    And User clicks 'Check out as guest' button
    And User checks selected item visibility
    And User checks selected item title is correct
    And User checks shipping fields visibility
    And User inputs shipping '<information>'
    And User clicks address 'Done' button
    And User checks payment fields visibility
    And User selects payment '<method>'
    Then 'Buy with '<method>'' button should be displayed

    Examples:
      | ItemPage                              | information                                        | method     |
      | https://www.ebay.com/itm/293701129540 | 4321,321,4132,4213,02154,gher@gmail.com,0634532632 | PayPal     |
      | https://www.ebay.com/itm/143727991859 | 4321,321,4132,4213,02154,gher@gmail.com,0634532632 | Google Pay |


  @smoke @login
  Scenario Outline: Purchase an item as registered user
    Given User is signed into account
    And User opens item page '<ItemPage>'
    When User clicks 'Buy it now' button
    And User check shipping information already present
    And User checks payment fields visibility
    And User selects payment '<method>'
    Then 'Buy with '<method>'' button should be displayed

    Examples:
      | ItemPage                              | method |
      | https://www.ebay.com/itm/143727991859 | PayPal |
      | https://www.ebay.com/itm/293701129540 | PayPal |

  @capcha @negative
  Scenario Outline: Purchase an item with invalid data
    Given User opens item page '<ItemPage>'
    When User clicks 'Buy it now' button
    And User clicks 'Check out as guest' button
    And User inputs shipping '<information>'
    And User clicks address 'Done' button
    And User checks phone number error message visibility
    And User inputs '<phone number>'
    And User clicks address 'Done' button
    And User selects payment '<method>'
    And User clicks card 'Done' button
    Then User check card error messages visibility

    Examples:
      | ItemPage                              | information                                  | phone number | method       |
      | https://www.ebay.com/itm/293701129540 | 4321,321,4132,4213,02154,gher@gmail.com,null | 2343252      | Add new card |
