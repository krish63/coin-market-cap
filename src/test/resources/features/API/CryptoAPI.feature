Feature: API Tests

  Scenario: I test Crypto Map API and perform a price conversion
    Given I set the API token in Context
    Then I get the crypto map
    And I store ID of "BTC,USDT,ETH"
    Then I should be able to Convert ID of "BTC,USDT,ETH" to "Bolivian Boliviano"


  Scenario: I test crypto Info API and check for its characteristics
    Given I set the API token in Context
    When I store the crypto info for "1027"
    And Logo URL should be "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png" for "1027"
    Then Currency symbol should be "ETH" for "1027"
    And technical_doc should be "https://github.com/ethereum/wiki/wiki/White-Paper" for "1027"
    Then Date added should be "2015-08-07T00:00:00.000Z" for "1027"
    #Etherium Response does not have Mineable tag hence below case would fail always
    And Response should have "mineable" tag for "1027"

  Scenario: I test Crypto info and list all Mineable APIs
    Given I set the API token in Context
    When I store the crypto info until 10 id
    And I store cryptos with "mineable" Tag for 10 cryptos
    And Response should have "mineable" tag for all Ids until 10