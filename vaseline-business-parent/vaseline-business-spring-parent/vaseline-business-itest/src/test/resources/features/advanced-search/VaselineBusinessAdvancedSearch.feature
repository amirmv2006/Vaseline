Feature: Vaseline Business Advanced Search
  Scenario: Business Layer Advanced Search
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     |
      | Sabzevar  | Khorasan      |
      | Amsterdam | North Holland |
      | Delft     | South Holland |
      | Rotterdam | South Holland |
      | NotACity  | Hollander     |
    When I count cities whose state's name ends with 'Holland'
    Then I get 3 as the count result