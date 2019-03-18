package ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.ExceptionConversionException
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator
import spock.lang.Specification
import spock.lang.Unroll

class IDefaultExceptionConverterTest extends Specification {

    def "InjectExceptionHandler"() {
        given:
        def underTest = Spy(TestInnerIDefaultExceptionConverter)
        def excHndler = Mock(ICoreExceptionHandler)
        when:
        underTest.injectExceptionHandler(excHndler)
        then:
        1 * excHndler.registerExceptionConverter(_, _)
    }

    @Unroll
    def "ConvertException Success with successful-translation=#isTranslationSuccess"() {
        given:
        def mockedTranslator = Mock(IVaselineMessageTranslator)
        def underTest = Spy(TestInnerIDefaultExceptionConverter)
        underTest.getMessageTranslator() >> mockedTranslator
        def exception = Mock(Exception)
        def excMsg = "Test Message"
        exception.getMessage() >> excMsg
        if (isTranslationSuccess) {
            mockedTranslator.getMessage(excMsg, _) >> expectedMsg
        } else {
            mockedTranslator.getMessage(excMsg, _) >> {throw new Exception("Expected")}
        }
        when:
        def result = underTest.convertException(exception)
        then:
        underTest.getClientExceptionClass() == result.class
        expectedMsg == result.getMessage()
        where:
        expectedMsg         | isTranslationSuccess
        "TranslatedMessage" | true
        "Test Message"      | false
    }

    def "ConvertException Problem when creating client exception"() {
        given:
        IVaselineMessageTranslator mockedTranslator = Mock(IVaselineMessageTranslator)
        def underTest = Spy(TestInnerIDefaultExceptionConverter)
        underTest.getMessageTranslator() >> mockedTranslator
        def exception = Mock(Exception)
        def excMsg = "Test Message"
        exception.getMessage() >> excMsg
        mockedTranslator.getMessage(excMsg, _) >> excMsg
        underTest.getClientExceptionClass() >> {throw new Exception("Expected")}
        when:
        underTest.convertException(exception)
        then:
        thrown(ExceptionConversionException)
    }

    private class TestInnerIDefaultExceptionConverter
            implements IDefaultExceptionConverter<Exception, BaseVaselineClientException> {

        TestInnerIDefaultExceptionConverter() {
        }

        @Override
        IVaselineMessageTranslator getMessageTranslator() {
            return null
        }

        @Override
        Class<Exception> getExceptionClass() {
            return Exception
        }

        @Override
        Class<BaseVaselineClientException> getClientExceptionClass() {
            return BaseVaselineClientException
        }

        @Override
        void setExceptionHandler(ICoreExceptionHandler exceptionHandler) {

        }
    }
}
