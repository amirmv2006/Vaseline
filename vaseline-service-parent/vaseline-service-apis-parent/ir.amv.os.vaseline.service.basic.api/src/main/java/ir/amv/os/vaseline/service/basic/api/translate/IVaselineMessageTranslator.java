package ir.amv.os.vaseline.service.basic.api.translate;

import java.util.List;
import java.util.Locale;

/**
 * overloaded methods without locale arg will use current locale from {@link IVaselineLocaleHolder#getCurrentLocale()}
 * Created by AMV on 10/1/2017.
 */
public interface IVaselineMessageTranslator {

    String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageI18nException;
    String getMessage(String code, Object[] args) throws NoSuchMessageI18nException;

    String getMessage(String code, Object[] args, String defaultMessage, Locale locale);
    String getMessage(String code, Object[] args, String defaultMessage);

    String getMessageForAny(List<String> codes, String defaultMessage, Locale locale);
    String getMessageForAny(List<String> codes, String defaultMessage);



    // translating classes
    String translateClass(Class<?> objClass, Locale locale);
    String translateClass(Class<?> objClass);

    String translateProperty(String objClass, String propertyName, Locale locale);
    String translateProperty(String objClass, String propertyName);

    String translateProperty(Class<?> objClass, String propertyName, Locale locale);
    String translateProperty(Class<?> objClass, String propertyName);

}
