Feature: Home Page Table Size & Filters
  The Crypto Data table filters and size dropdowns should work correctly

  Scenario: I select dropdown value to 100
    Given I navigate to "https://coinmarketcap.com/"
    Then I take screenshot
    And I click on close popup
    Then I click on size dropdown
    When I click on dropdown value 100
    Then I should get the table should have 100 rows

  Scenario: I apply & validate price and Mcap Filters
    Given I navigate to "https://coinmarketcap.com/"
    Then I take screenshot
    Then I click on filters
    Then I click on clear filters
    And I click on filters
    Then I click on add filters
    Then I set the price filter from 101 to 1000
    And I set the market cap from "1000000000" to "10000000000"
    When I click on show results
    Then I wait for 3 sec
    Then I should have all the rows market cap from "1000000000" to "10000000000"
    And I should have all the rows price  from "100" to "1000"
