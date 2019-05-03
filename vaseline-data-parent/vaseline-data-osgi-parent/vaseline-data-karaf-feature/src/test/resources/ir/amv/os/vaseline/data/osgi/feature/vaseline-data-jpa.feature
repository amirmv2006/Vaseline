Feature: Vaseline Data JPA
  Scenario: Test Basic API
    Given I have Vaseline Data with prerequisites and Test JPA model
    When start karaf
    And register Sample Basic Dao
    Then save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And getById return entity with firstName="TestFirstName" and lastName="TestLastName"
    And count returns more than 0 records
    And getAll contains a SampleEntity with firstName="TestFirstName" and lastName="TestLastName"
    And update Sample Entity with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
    And getById return entity with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
  Scenario: Test Simple Search API
    Given I have Vaseline Data with prerequisites and Test JPA model
    When start karaf
    And register Sample Basic Dao
    And register Sample Simple Search Dao
    Then save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And countByExample works with firstName="FirstName" and lastName="LastName" returns more than 0 records
    And searchByExample works with firstName="FirstName" and lastName="LastName" contains an entity with firstName="TestFirstName" and lastName="TestLastName"