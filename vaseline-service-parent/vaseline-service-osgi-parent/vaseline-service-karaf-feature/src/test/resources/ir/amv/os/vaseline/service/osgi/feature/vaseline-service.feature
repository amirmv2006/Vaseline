Feature: Vaseline Data
  Scenario: Check Feature vaseline-service-karaf-feature without search
    Given I have karaf
    And feature with groupId="com.github.amirmv2006.service.osgi", artifactId="vaseline-service-karaf-feature" and name="ir.amv.os.vaseline.service.basic.api,ir.amv.os.vaseline.service.basic.def,ir.amv.os.vaseline.service.multidao.api,ir.amv.os.vaseline.service.multidao.def" is deployed
    And start bundle with groupId="org.apache.geronimo.specs" and artifactId="geronimo-jpa_2.1_spec" and version as in project
    When start karaf
    Then bundle "ir.amv.os.vaseline.service.basic.api" is started
    Then bundle "ir.amv.os.vaseline.service.basic.def" is started
    Then bundle "ir.amv.os.vaseline.service.multidao.api" is started
    Then bundle "ir.amv.os.vaseline.service.multidao.def" is started
    Then bundle "ir.amv.os.vaseline.service.search.simple.api" is not deployed
    Then bundle "ir.amv.os.vaseline.service.search.simple.def" is not deployed
    Then bundle "ir.amv.os.vaseline.service.search.advanced.api" is not deployed
    Then bundle "ir.amv.os.vaseline.service.search.advanced.def" is not deployed
  Scenario: Check Feature vaseline-business-karaf-feature
    Given I have karaf
    And feature with groupId="com.github.amirmv2006.service.osgi", artifactId="vaseline-service-karaf-feature" and name="vaseline-service-karaf-feature" is deployed
    And start bundle with groupId="org.apache.geronimo.specs" and artifactId="geronimo-jpa_2.1_spec" and version as in project
    When start karaf
    Then bundle "ir.amv.os.vaseline.service.basic.api" is started
    Then bundle "ir.amv.os.vaseline.service.basic.def" is started
    Then bundle "ir.amv.os.vaseline.service.search.simple.api" is started
    Then bundle "ir.amv.os.vaseline.service.search.simple.def" is started
    Then bundle "ir.amv.os.vaseline.service.search.advanced.api" is started
    Then bundle "ir.amv.os.vaseline.service.search.advanced.def" is started
    Then bundle "ir.amv.os.vaseline.service.multidao.api" is started
    Then bundle "ir.amv.os.vaseline.service.multidao.def" is started