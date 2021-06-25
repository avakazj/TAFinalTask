Feature: Main
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check that search results contains search keywords
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    Then User checks if items shown contains search keywords '<keyword>'

    Examples:
      | homePage              | keyword        |
      | https://www.asos.com/ | North Face Cap |
      | https://www.asos.com/ | adidas         |

  Scenario Outline: Check if free delivery applied
    Given User opens '<homePage>' page
    And User makes search by keyword '<keyword>'
    And User filters products by '<brand>'
    And User filters products by price in range <minValue>, <maxValue>
    And User set sort order '<sortingOrder>'
    When User clicks first product
    And User clicks 'Put to Bag' button
    And User opens shopping cart
    And User checks that delivery payment is added
    And User set quantity of items to '<quantity>'
    And User clicks Update bag button
    Then User checks that there is no delivery payment added

    Examples:
      | homePage              | keyword | brand  | minValue | maxValue | sortingOrder      | quantity |
      | https://www.asos.com/ | T Shirt | adidas | 30       | 100      | Price low to high | 5        |

  Scenario Outline: Check save for later functionality
    Given User opens '<homePage>' page
    And User makes search by keyword '<keyword>'
    And User saves several products '<indexes>' by clicking Heart icon on product image
    When User opens 'Save for later' page by clicking 'Heart' icon in header
    Then User checks if items in list are the same that he added '<indexes>'

    Examples:
      | homePage              | keyword        | indexes |
      | https://www.asos.com/ | North Face Cap | 1, 2, 4 |

  Scenario Outline: Check that currency changes
    Given User opens '<homePage>' page
    And User clicks Flag icon
    When User selects USD currency
    And User clicks Update button
    And User makes search by keyword '<keyword>'
    Then User checks that currency in products price is USD

    Examples:
      | homePage              | keyword        |
      | https://www.asos.com/ | North Face Cap |

  Scenario Outline: User looking for specified item using filters and checks if product code search works
    Given User opens '<homePage>' page
    And User clicks 'Shop Women'
    And User clicks dropdown menu Accessories -> Belts
    And User filters products by Brand '<brand>'
    And User filters products by 'Sale-New Season' -> 'Sale'
    And User filters products by 'Style' -> 'Waist'
    And User filters products by 'Leather-Non Leather' -> 'Non Leather'
    And User filters products by 'Colour' -> 'Black'
    And User click on desired item '<title>'
    When User saves product code
    And User clicks on Logo
    And  User makes search by keyword '<keyword>'
    Then User checks that products same

    Examples:
      | homePage              | keyword | brand       | title                                                           |
      | https://www.asos.com/ | 1721860 | ASOS DESIGN | ASOS DESIGN bug detail buckle waist and hip jeans belt in black |

  Scenario Outline:  User uses Fit Assistant and checks that advise is shown after on PDP
    Given User opens '<homePage>' page
    And User clicks 'Shop Men'
    And User clicks 'Jeans' tile in a front sale section
    And User clicks first product
    And User clicks 'Find your Fit Assistant size
    When User enters height and weight'<height>','<weight>'
    And User clicks 'Continue' button
    And User checks if radiobutton is visible
    And User clicks 'Continue' button
    And User enters age'<age>'
    And User clicks 'Continue' button
    And User set fit preference to 'On the loose side'
    And User clicks 'Continue' button
    And User chooses Levis
    And User selects 'Regular Jeans' type
    And User skips question
    And User selects L size
    And User clicks 'Continue' button
    And User clicks 'Continue shopping'
    Then User checks that size fit advise is shown

    Examples:
    | homePage              | height | weight | age |
    | https://www.asos.com/ | 189    | 45     | 37  |
