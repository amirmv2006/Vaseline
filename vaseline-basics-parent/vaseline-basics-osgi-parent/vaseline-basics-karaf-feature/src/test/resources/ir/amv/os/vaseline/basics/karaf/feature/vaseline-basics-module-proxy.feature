Feature: Module Proxy
  Scenario: Proxy an OSGi Service
    Given I have karaf
    And feature with groupId="ir.amv.os.vaseline.basics.osgi", artifactId="vaseline-basics-karaf-feature" and name="vaseline-core" is deployed
    When start karaf
    And I register Sample Proxy Interceptor
    And I regsiter a service which needs proxy
    And I call a method from registered service
    Then Sample Proxy Interceptor is called