Feature: Vaseline Rest Advanced Search
  Scenario: Rest Layer Advanced Search
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     |
      | Sabzevar  | Khorasan      |
      | Amsterdam | North Holland |
      | Delft     | South Holland |
      | Rotterdam | South Holland |
      | NotACity  | Hollander     |
    When I count cities whose state's name starts with 'N'
    Then I get a city named 'Amsterdam' as result