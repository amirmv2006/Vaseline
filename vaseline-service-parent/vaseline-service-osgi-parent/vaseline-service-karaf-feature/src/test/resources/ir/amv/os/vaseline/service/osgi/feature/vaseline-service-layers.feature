Feature: Vaslien Service Layer Feature
  Scenario: Test Basic Service
    Given I have Vaseline Service with prerequisites and Test JPA model
    When start karaf
    Then verify Basic Dao is registered
    And verify Basic Business is registered
    And verify Basic Service is registered
    And service layer save Sample Dto with firstName="TestFirstName" and lastName="TestLastName"
    And service layer getById return dto with firstName="TestFirstName" and lastName="TestLastName"
    And service layer count returns more than 0 records
    And service layer getAll contains a SampleDto with firstName="TestFirstName" and lastName="TestLastName"
    And service layer update Sample Dto with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
    And service layer getById return dto with firstName="UpdatedFirstName" and lastName="UpdatedLastName"
  Scenario: Test Simple Search Business API
    Given I have Vaseline Service with prerequisites and Test JPA model
    When start karaf
    Then verify Basic Dao is registered
    Then verify Basic Business is registered
    And verify SimpleSearch Dao is registered
    And verify SimpleSearch Business is registered
    And verify SimpleSearch Service is registered
    And service layer save Sample Dto with firstName="TestFirstName" and lastName="TestLastName"
    And service layer countByExample works with firstName="FirstName" and lastName="LastName" returns more than 0 records
    And service layer searchByExample works with firstName="FirstName" and lastName="LastName" contains a dto with firstName="TestFirstName" and lastName="TestLastName"
  Scenario: Test Advanced Search Data API
    Given I have Vaseline Service with prerequisites and Test JPA model
    When start karaf
    Then verify Basic Dao is registered
    Then verify Basic Business is registered
    And verify AdvancedSearch Dao is registered
    And verify AdvancedSearch Business is registered
    And verify AdvancedSearch Service is registered
    And service layer save Sample Dto with firstName="TestFirstName" and lastName="TestLastName"
    And service layer countBySearchObject works with firstName%="FirstName" and lastName%="LastName" returns more than 0 records
    And service layer searchBySearchObject works with firstName%="FirstName" and lastName%="LastName" contains a dto with firstName="TestFirstName" and lastName="TestLastName"