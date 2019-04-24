Feature: Module Cache
  Scenario: Cache OSGi Service
    Given I have karaf
    And feature with groupId="com.github.amirmv2006.basics.osgi", artifactId="vaseline-basics-karaf-feature" and name="vaseline-basics-cache-hazelcast" is deployed
    When start karaf
    And I have cache "testCache"
    And I put value "nana" with key "jijiri" to cache "testCache"
    Then I get value "nana" with key "jijiri" from cache "testCache"