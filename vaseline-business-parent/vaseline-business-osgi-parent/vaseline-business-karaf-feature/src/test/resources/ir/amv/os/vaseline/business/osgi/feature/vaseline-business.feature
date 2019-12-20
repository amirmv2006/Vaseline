Feature: Vaseline Data
  Scenario: Check Feature vaseline-business-karaf-feature without search
    Given I have karaf
    And feature with groupId="ir.amv.os.vaseline.business.osgi", artifactId="vaseline-business-karaf-feature" and name="ir.amv.os.vaseline.business.basic.api,ir.amv.os.vaseline.business.basic.def,ir.amv.os.vaseline.business.multidao.api,ir.amv.os.vaseline.business.multidao.def" is deployed
    And start bundle with groupId="org.apache.geronimo.specs" and artifactId="geronimo-jpa_2.1_spec" and version as in project
    When start karaf
    Then bundle "ir.amv.os.vaseline.business.basic.api" is started
    Then bundle "ir.amv.os.vaseline.business.basic.def" is started
    Then bundle "ir.amv.os.vaseline.business.multidao.api" is started
    Then bundle "ir.amv.os.vaseline.business.multidao.def" is started
    Then bundle "ir.amv.os.vaseline.business.search.simple.api" is not deployed
    Then bundle "ir.amv.os.vaseline.business.search.simple.def" is not deployed
    Then bundle "ir.amv.os.vaseline.business.search.advanced.api" is not deployed
    Then bundle "ir.amv.os.vaseline.business.search.advanced.def" is not deployed
  Scenario: Check Feature vaseline-business-karaf-feature
    Given I have karaf
    And feature with groupId="ir.amv.os.vaseline.business.osgi", artifactId="vaseline-business-karaf-feature" and name="vaseline-business-karaf-feature" is deployed
    And start bundle with groupId="org.apache.geronimo.specs" and artifactId="geronimo-jpa_2.1_spec" and version as in project
    When start karaf
    Then bundle "ir.amv.os.vaseline.business.basic.api" is started
    Then bundle "ir.amv.os.vaseline.business.basic.def" is started
    Then bundle "ir.amv.os.vaseline.business.search.simple.api" is started
    Then bundle "ir.amv.os.vaseline.business.search.simple.def" is started
    Then bundle "ir.amv.os.vaseline.business.search.advanced.api" is started
    Then bundle "ir.amv.os.vaseline.business.search.advanced.def" is started
    Then bundle "ir.amv.os.vaseline.business.multidao.api" is started
    Then bundle "ir.amv.os.vaseline.business.multidao.def" is started