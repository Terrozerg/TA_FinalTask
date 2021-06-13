Feature: deals
  As a user
  I want to have deals page
  So i can see discounted products

  @smoke
  Scenario: View discounted products
    Given User opens home page
    And User checks 'Daily deals' button visibility
    When User clicks 'Daily deals' button
    And User checks spotlight item visibility
    And User checks featured items visibility
    And User clicks spotlight item
    And User checks item price visibility
    And User check item title visibility
    Then User checks item is discounted
