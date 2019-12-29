Feature: Vaseline Rest Basic
  Scenario: Rest Layer getAll
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     |
      | Sabzevar  | Khorasan      |
      | Shahrud   | Semnan        |
      | Amsterdam | North Holland |
      | Delft     | South Holland |
      | Rotterdam | South Holland |
    When I send 'GET' on '/cityCrud' endpoint
    Then I get 5 Cities as the result