Feature: Vaseline Data Base Repositories

  Scenario: Counting a read-only repository
    Given Spring setup with embedded DB with cities
      | CityName  |
      | Sabzevar  |
      | Shahrud   |
      | Amsterdam |
      | Delft     |
      | Rotterdam |
    When I query city count
    Then I get 5 as count result

  Scenario: Having a read-only repository
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     |
      | Sabzevar  | Khorasan      |
      | Shahrud   | Semnan        |
      | Amsterdam | North Holland |
      | Delft     | South Holland |
      | Rotterdam | South Holland |
    When I query province count
    Then I get 4 as count result

  Scenario Outline: Existing Spring Repository functionality should still work
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     |
      | Sabzevar  | Khorasan      |
      | Shahrud   | Semnan        |
      | Amsterdam | North Holland |
    When I query city by name '<queryCityName>'
    Then I should get back <expectedResultCount> city named '<expectedResultCityName>'
    Examples:
      | queryCityName | expectedResultCount | expectedResultCityName |
      | Sabzevar      | 1                   | Sabzevar               |
      | Sa            | 0                   | N/A                    |