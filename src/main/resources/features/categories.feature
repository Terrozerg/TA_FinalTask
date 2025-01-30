Feature: shop by category
  As a user
  I want to have categories
  So i can browse categorised items

  @smoke @test
  Scenario Outline: Select item by category
    Given User opens home page
    And User checks 'shop by category' button visibility
    When User clicks 'shop by category' button
    And User checks categories visibility
    And User selects <category>
    And User checks sub-categories visibility
    And User checks limited deals visibility
    And User selects category <sub-category>
    And User clicks first item in listing
    Then User check item title visibility

    Examples:
      | category              | sub-category         |
      | "Computers & tablets" | "Laptops & Netbooks" |
      | "Cameras & Photo"     | "Digital Cameras"    |
      | "Cameras & Photo"     | "Lighting & Studio"  |


  @smoke
  Scenario Outline: Select item by brand
    Given User opens home page
    And User checks 'shop by category' button visibility
    When User clicks 'shop by category' button
    And User checks categories visibility
    And User selects <category>
    And User selects category by '<brand>'
    And User clicks 'Shop now' button
    And User clicks first item in listing
    Then Item name should contain selected '<brand>'


    Examples:
      | category          | brand  |
      | "String"          | Kala   |
      | "Golf"            | Mizuno |
      | "Cameras & Photo" | Sony   |
