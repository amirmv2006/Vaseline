Feature: Vaseline Data
  Scenario: Check Feature vaseline-data-karaf-feature-hibernate
    Given I have karaf
    And feature with groupId="ir.amv.os.vaseline.data.osgi", artifactId="vaseline-data-karaf-feature" and name="vaseline-data-karaf-feature-hibernate" is deployed
    When start karaf
    Then bundle "ir.amv.os.vaseline.data.dao.basic.api" is started
    Then bundle "ir.amv.os.vaseline.data.search.simple.api" is started
    Then bundle "ir.amv.os.vaseline.data.search.advanced.api" is started
    Then bundle "ir.amv.os.vaseline.data.hibernate.dao.basic.api" is started
    Then bundle "ir.amv.os.vaseline.data.hibernate.search.simple.api" is started
    Then bundle "ir.amv.os.vaseline.data.hibernate.search.advanced.api" is started
  Scenario: Check Feature vaseline-data-karaf-feature-jpa
    Given I have karaf
    And feature with groupId="ir.amv.os.vaseline.data.osgi", artifactId="vaseline-data-karaf-feature" and name="vaseline-data-karaf-feature-jpa" is deployed
    And start bundle with groupId="org.apache.geronimo.specs" and artifactId="geronimo-jpa_2.1_spec" and version as in project
    When start karaf
    Then bundle "ir.amv.os.vaseline.data.dao.basic.api" is started
    Then bundle "ir.amv.os.vaseline.data.search.simple.api" is started
    Then bundle "ir.amv.os.vaseline.data.search.advanced.api" is started
    Then bundle "ir.amv.os.vaseline.data.jpa.dao.basic.api" is started
    Then bundle "ir.amv.os.vaseline.data.jpa.search.simple.api" is started
    Then bundle "ir.amv.os.vaseline.data.jpa.search.advanced.api" is started