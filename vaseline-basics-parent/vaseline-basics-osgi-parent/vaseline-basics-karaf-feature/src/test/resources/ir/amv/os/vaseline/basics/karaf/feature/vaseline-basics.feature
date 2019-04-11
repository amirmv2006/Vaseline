Feature: Vaseline Basics

  Scenario: Check Feature vaseline-logger-slf4j
    Given I have karaf
    And feature with groupId="com.github.amirmv2006.basics.osgi", artifactId="vaseline-basics-karaf-feature" and name="vaseline-basics-logging-common,vaseline-logger-slf4j" is deployed
    When start karaf
    Then bundle "ir.amv.os.vaseline.basics.logging.slf4j.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger" is registered

  Scenario: Check Feature vaseline-basics-logging-log-service
    Given I have karaf
    And feature with groupId="com.github.amirmv2006.basics.osgi", artifactId="vaseline-basics-karaf-feature" and name="vaseline-basics-logging-common,vaseline-basics-logging-log-service" is deployed
    When start karaf
    Then bundle "ir.amv.os.vaseline.basics.logging.loggingservice.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger" is registered

  Scenario: Check Feature vaseline-basics-karaf-feature
    Given I have karaf
    And feature with groupId="com.github.amirmv2006.basics.osgi", artifactId="vaseline-basics-karaf-feature" and name="vaseline-basics-karaf-feature" is deployed
    When start karaf
    Then bundle "ir.amv.os.vaseline.thirdparty.shared.util.reflection" is started
    And bundle "ir.amv.os.vaseline.basics.i18n.api" is started
    And bundle "ir.amv.os.vaseline.basics.jdbc.api" is started
    And bundle "ir.amv.os.vaseline.basics.json.api" is started
    And bundle "ir.amv.os.vaseline.basics.logging.api" is started
    And bundle "ir.amv.os.vaseline.basics.logging.def" is started
    And bundle "ir.amv.os.vaseline.basics.core.api" is started
    And bundle "ir.amv.os.vaseline.basics.dao.api" is started
    And bundle "ir.amv.os.vaseline.basics.mapper.api" is started
    And bundle "ir.amv.os.vaseline.basics.validation.api" is started
    And bundle "ir.amv.os.vaseline.basics.base.osgi" is started
    And bundle "ir.amv.os.vaseline.basics.cache.hazelcast.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.cache.api.server.IVaselineCacheApi" is registered
    And Service of type "ir.amv.os.vaseline.basics.cache.api.server.IVaselineCacheConfigurer" is registered
    And bundle "ir.amv.os.vaseline.basics.core.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler" is registered
    And Service of type "ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.IBaseExceptionConverter" is registered
    And bundle "ir.amv.os.vaseline.basics.i18n.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator" is registered
    And Service of type "ir.amv.os.vaseline.basics.i18n.api.server.file.resolver.IVaselineI18nFileProvider" is registered
    And bundle "ir.amv.os.vaseline.basics.validation.hibval.osgi" is started
    And Service of type "javax.validation.Validator" is registered
    And bundle "ir.amv.os.vaseline.basics.mapper.dozer.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper" is registered
    And bundle "ir.amv.os.vaseline.basics.logging.pax.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger" is registered
    And bundle "ir.amv.os.vaseline.basics.logging.common.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer" is registered
    And bundle "ir.amv.os.vaseline.basics.json.gson.osgi" is started
    And Service of type "ir.amv.os.vaseline.basics.core.api.server.polymorphysm.IVaselinePolymorphysmClassHolder" is registered
    And Service of type "ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter" is registered in 5 seconds
