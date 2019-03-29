Feature: VaselineSlf4jLogFeatureIntegrationTest

  Scenario: Slf4j
    Given I have karaf
    When run karaf
    Then karaf is started