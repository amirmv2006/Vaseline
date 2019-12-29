Feature: Vaseline Data Advanced Search

  Scenario Outline: Advanced search query contains
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     | CountryName | CountryPopulation |
      | Sabzevar  | Khorasan      | Iran        | 81000000          |
      | Shahrud   | Semnan        | Iran        | 81000000          |
      | Amsterdam | North Holland | Netherlands | 17000000          |
      | Delft     | South Holland | Netherlands | 17000000          |
      | Rotterdam | South Holland | Netherlands | 17000000          |
    When I query for cities whose name contains with <queryContains>
    Then I get back cities with name <results>
    Examples:
      | queryContains | results                                             |
      | 'a'           | '["Sabzevar", "Shahrud", "Amsterdam", "Rotterdam"]' |
      | 'terdam'      | '["Amsterdam", "Rotterdam"]'                        |
      | 's'           | '["Amsterdam"]'                                     |
      | 'Am'          | '["Amsterdam"]'                                     |
      | 'p'           | '[]'                                                |

  Scenario Outline: Advanced Search query with join
    Given Spring setup with embedded DB with cities
      | CityName  | StateName     | CountryName | CountryPopulation |
      | Sabzevar  | Khorasan      | Iran        | 81000000          |
      | Shahrud   | Semnan        | Iran        | 81000000          |
      | Amsterdam | North Holland | Netherlands | 17000000          |
      | Delft     | South Holland | Netherlands | 17000000          |
      | Rotterdam | South Holland | Netherlands | 17000000          |
      | NaKojAbad |               |             |                   |
    When I query for cities whose country's name contains <queryContains>
    Then I get back cities with name <results>
    Examples:
      | queryContains | results                                                       |
      | 'an'          | '["Sabzevar", "Shahrud", "Amsterdam", "Delft", "Rotterdam"]'  |
      | 'Iran'        | '["Sabzevar", "Shahrud"]'                                     |
      | 'ther'        | '["Amsterdam", "Delft", "Rotterdam"]'                         |
      | 'Nal'         | '[]'                                                          |