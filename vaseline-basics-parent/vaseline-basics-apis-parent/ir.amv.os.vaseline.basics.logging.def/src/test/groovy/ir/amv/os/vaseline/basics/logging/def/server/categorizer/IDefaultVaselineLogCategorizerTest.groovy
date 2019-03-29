package ir.amv.os.vaseline.basics.logging.def.server.categorizer


import spock.lang.Specification
import spock.lang.Unroll

import static ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel.DEBUG
import static ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel.INFO

class IDefaultVaselineLogCategorizerTest extends Specification {
    def "Priority"() {
        given:
        IDefaultVaselineLogCategorizer underTest = Spy(IDefaultVaselineLogCategorizer)
        when:
        def result = underTest.priority()
        then:
        Integer.MAX_VALUE == result
    }

    @Unroll
    def "GetLoggerFor for name='#name', category='#category'"() {
        given:
        def underTest = Spy(IDefaultVaselineLogCategorizer)
        when:
        def result = underTest.getLoggerFor(name, category)
        then:
        expected == result
        where:
        expected    | name      | category
        "Test"      | "Test"    | "Whatever"
        ""          | ""        | "category"
    }

    @Unroll
    def "PrepareLog for source='#source', category='#category', level='#level', msgFormat='#format', args='#args'"() {
        given:
        def underTest = Spy(IDefaultVaselineLogCategorizer)
        when:
        def result = underTest.prepareLog(source, category, level, msgFormat, args)
        then:
        expected == result
        where:
        expected                        | source    | category      | level     | msgFormat                 | args
        "an unformatted message"        | "Test"    | "Whatever"    | INFO      | "an unformatted message"  | null
        "an %s message"                 | ""        | "category"    | DEBUG     | "an %s message"           | null
        "an arg message"                | ""        | "category"    | DEBUG     | "an %s message"           | "arg"
    }

}
