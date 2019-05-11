Feature: Vaslien Business Layer Feature
  Scenario: Test Basic Business API
    Given I have Vaseline Business with prerequisites and Test JPA model
    When start karaf
    Then verify Basic Dao is registered
    And verify Basic Business is registered
    And business layer save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And business layer getById return entity with firstName="TestFirstName" and lastName="TestLastName"
    And business layer count returns more than 0 records
    And business layer getAll contains a SampleEntity with firstName="TestFirstName" and lastName="TestLastName"
    And business layer update Sample Entity with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
    And business layer getById return entity with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
  Scenario: Test Simple Search Business API
    Given I have Vaseline Business with prerequisites and Test JPA model
    When start karaf
    Then verify Basic Dao is registered
    And verify SimpleSearch Dao is registered
    And verify SimpleSearch Business is registered
    And business layer save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And business layer countByExample works with firstName="FirstName" and lastName="LastName" returns more than 0 records
    And business layer searchByExample works with firstName="FirstName" and lastName="LastName" contains an entity with firstName="TestFirstName" and lastName="TestLastName"
  Scenario: Test Advanced Search Data API
    Given I have Vaseline Business with prerequisites and Test JPA model
    When start karaf
    Then verify Basic Dao is registered
    And verify AdvancedSearch Dao is registered
    And verify AdvancedSearch Business is registered
    And business layer save Sample Entity with firstName="TestFirstName" and lastName="TestLastName"
    And business layer countBySearchObject works with firstName%="FirstName" and lastName%="LastName" returns more than 0 records
    And business layer searchBySearchObject works with firstName%="FirstName" and lastName%="LastName" contains an entity with firstName="TestFirstName" and lastName="TestLastName"