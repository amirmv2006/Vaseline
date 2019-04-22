package ir.amv.os.vaseline.basics.logging.def.server.logger

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer
import ir.amv.os.vaseline.basics.logging.api.server.exc.LogInterruptOthersException
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel
import ir.amv.os.vaseline.basics.logging.api.server.obj.ILogObject
import spock.lang.Specification
import spock.lang.Unroll

class IDefaultVaselineLoggerTest extends Specification {
    def "DoLogWithCategory"() {
        given:
        IDefaultVaselineLogger underTest = Spy(IDefaultVaselineLogger)
        def loggerName = "aLogger"
        def category = "aCategory"
        def level = VaselineLogLevel.INFO
        def logMessage = "aMessage"
        when:
        underTest.doLogWithCategory(loggerName, category, level, logMessage)
        then:
        1 * underTest.doLog(loggerName, level, logMessage) >> {}
    }

    def "IsLogLevelEnabled"() {
        given:
        IDefaultVaselineLogger underTest = Spy(IDefaultVaselineLogger)
        when:
        def result = underTest.isLogLevelEnabled(VaselineLogLevel.INFO)
        then:
        result
    }

    def "LogWithOrderedCategorizersAndInterruption"() {
        given:
        IDefaultVaselineLogger underTest = Spy(IDefaultVaselineLogger)
        def source = "aLogger"
        def category = "aCategory"
        def level = VaselineLogLevel.INFO
        def fMsg = "aMessage"
        def args = "logParam"
        def mockedCategorizer1 = Mock(IVaselineLogCategorizer)
        def mockedCategorizer2 = Mock(IVaselineLogCategorizer)
        def mockedCategorizer3 = Mock(IVaselineLogCategorizer)
        def categorizerList = [mockedCategorizer1, mockedCategorizer2, mockedCategorizer3]
        categorizerList.forEach({
            it.priority() >> categorizerList.indexOf(it)
            it.getLoggerFor(source, category) >> source
        })
        underTest.getLogCategorizers() >> categorizerList
        when:
        underTest.log(source, category, level, fMsg, args)
        then:
        1 * mockedCategorizer1.prepareLog(source, category, level, fMsg, args) >> "firstLog"
        1 * underTest.doLogWithCategory(source, category, level, "firstLog") >> {}
        then:
        1 * mockedCategorizer2.prepareLog(source, category, level, fMsg, args) >> {throw new LogInterruptOthersException("secondLog")}
        1 * underTest.doLogWithCategory(source, category, level, "secondLog") >> {}
        0 * mockedCategorizer3.prepareLog(_, _, _, _, _)
        0 * mockedCategorizer3.getLoggerFor(_, _)
    }

    @Unroll
    def "ToLogString for #input"() {
        given:
        IDefaultVaselineLogger underTest = Spy(IDefaultVaselineLogger)
        when:
        def result = underTest.toLogString(input)
        then:
        expected == result
        where:
        expected                                | input
        "null"                                  | null
        "aString"                               | "aString"
        "[1, 2, 3]"                             | [1,2,3]
        '{"key1":"value1", "key2":"value2"}'    | [key1:"value1",key2:"value2"]
    }

    def "ToLogString for ILogObject"() {
        given:
        IDefaultVaselineLogger underTest = Spy(IDefaultVaselineLogger)
        def input = Mock(ILogObject)
        def expected = "logObjToStr"
        when:
        def result = underTest.toLogString(input)
        then:
        1 * input.toLogString() >> expected
        expected == result
    }
}
