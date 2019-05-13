Feature: Vaseline Data JPA
  Scenario: Test Basic Data API
    Given I have Vaseline Data with prerequisites and Test JPA model
    When start karaf
    Then verify Basic Dao is registered
    And save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And getById return entity with firstName="TestFirstName" and lastName="TestLastName"
    And count returns more than 0 records
    And getAll contains a SampleEntity with firstName="TestFirstName" and lastName="TestLastName"
    And update Sample Entity with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
    And getById return entity with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
  Scenario: Test Simple Search Data API
    Given I have Vaseline Data with prerequisites and Test JPA model
    When start karaf
    Then verify SimpleSearch Dao is registered
    And save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And countByExample works with firstName="FirstName" and lastName="LastName" returns more than 0 records
    And searchByExample works with firstName="FirstName" and lastName="LastName" contains an entity with firstName="TestFirstName" and lastName="TestLastName"
  Scenario: Test Advanced Search Data API
    Given I have Vaseline Data with prerequisites and Test JPA model
    When start karaf
    Then verify AdvancedSearch Dao is registered
    And save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And countBySearchObject works with firstName%="FirstName" and lastName%="LastName" returns more than 0 records
    And searchBySearchObject works with firstName%="FirstName" and lastName%="LastName" contains an entity with firstName="TestFirstName" and lastName="TestLastName"