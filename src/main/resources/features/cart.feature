Feature: cart
  As a user
  I want a cart
  So i can store items for later checkout

  @smoke
  Scenario Outline: Add item to cart
    Given User opens item page '<ItemPage>'
    And User checks 'Add to cart' button visibility
    And User check item title visibility
    When User adds item to cart
    And User checks that cart is not empty
    And User checks 'Go to checkout' button visibility
    And User checks 'Remove' button visibility
    Then Cart should contain this item

    Examples:
      | ItemPage                              |
      | https://www.ebay.com/itm/124680059923 |
      | https://www.ebay.com/itm/393182867855 |
      | https://www.ebay.com/itm/402908800786 |
      | https://www.ebay.com/itm/131781107926 |