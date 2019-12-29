Feature: Vaseline Business Basic
  Scenario: Business Layer getAll
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     |
      | Sabzevar  | Khorasan      |
      | Shahrud   | Semnan        |
      | Amsterdam | North Holland |
      | Delft     | South Holland |
      | Rotterdam | South Holland |
    When I call getAll on Business Layer
    Then I get 5 Cities as the result
    And The lazy property "state" for these cities is not loaded
  Scenario: Business Layer getByState lazy load
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     |
      | Sabzevar  | Khorasan      |
      | Shahrud   | Semnan        |
      | Amsterdam | North Holland |
      | Delft     | South Holland |
      | Rotterdam | South Holland |
    When I get cities of province with name 'South Holland'
    Then The lazy property "state" for these cities is loaded