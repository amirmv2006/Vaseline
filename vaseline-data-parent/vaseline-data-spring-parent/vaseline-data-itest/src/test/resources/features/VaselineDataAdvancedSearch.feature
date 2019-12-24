Feature: Vaseline Data Advanced Search
  Scenario: advanced search query
    Given Spring setup with embedded DB
    When I query for cities whose name contains with 'a'
    Then I get back cities with name 'Amsterdam' and 'Sabzevar'
