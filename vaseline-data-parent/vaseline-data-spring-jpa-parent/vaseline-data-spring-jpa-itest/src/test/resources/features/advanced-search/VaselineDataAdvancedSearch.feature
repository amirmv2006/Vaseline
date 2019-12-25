Feature: Vaseline Data Advanced Search
 Scenario Outline: Advanced search query contains
    Given Spring setup with embedded DB with cities
      | CityName  |
      | Sabzevar  |
      | Shahrud   |
      | Amsterdam |
      | Delft     |
      | Rotterdam |
    When I query for cities whose name contains with <queryContains>
    Then I get back cities with name <results>
   Examples:
    | queryContains | results                                             |
    | 'a'           | '["Sabzevar", "Shahrud", "Amsterdam", "Rotterdam"]' |
    | 'terdam'      | '["Amsterdam", "Rotterdam"]'                        |
    | 's'           | '["Amsterdam"]'                                     |
    | 'Am'          | '["Amsterdam"]'                                     |
    | 'p'           | '[]'                                                |
