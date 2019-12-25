Feature: Vaseline Data Base Repositories
  Scenario: Having a read-only repository
    Given Spring setup with embedded DB with cities
      | CityName  |
      | Sabzevar  |
      | Shahrud   |
      | Amsterdam |
      | Delft     |
      | Rotterdam |
    When I query city count
    Then I get 5 as a result
 Scenario Outline: Existing Spring Repository functionality should still work
    Given Spring setup with embedded DB with cities
      | CityName  |
      | Sabzevar  |
      | Shahrud   |
      | Amsterdam |
    When I query city by name '<queryCityName>'
    Then I should get back <expectedResultCount> city named '<expectedResultCityName>'
   Examples:
   | queryCityName  | expectedResultCount | expectedResultCityName  |
   | Sabzevar       | 1                   | Sabzevar                |
   | Sa             | 0                   | N/A                     |