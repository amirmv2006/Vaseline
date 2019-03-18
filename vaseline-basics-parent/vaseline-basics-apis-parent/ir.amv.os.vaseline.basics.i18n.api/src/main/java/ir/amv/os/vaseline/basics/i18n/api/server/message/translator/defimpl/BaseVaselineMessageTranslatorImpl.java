package ir.amv.os.vaseline.basics.i18n.api.server.message.translator.defimpl;

import ir.amv.os.vaseline.basics.i18n.api.server.exc.NoSuchMessageI18nException;
import ir.amv.os.vaseline.basics.i18n.api.server.locale.IVaselineLocaleHolder;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by AMV on 10/1/2017.
 */
public abstract class BaseVaselineMessageTranslatorImpl
        implements IVaselineMessageTranslator {

    protected IVaselineLocaleHolder localeHolder;

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        try {
            return getMessage(code, args, locale);
        } catch (NoSuchMessageI18nException ignored) {
            return defaultMessage;
        }
    }

    @Override
    public String getMessageForAny(List<String> codes, String defaultMessage, Locale locale) {
        for (String possibleKey : codes) {
            try {
                return getMessage(possibleKey, new Object[]{}, locale);
            } catch (NoSuchMessageI18nException ignored) {
            }
        }
        return defaultMessage;
    }

    @Override
    public String translateClass(Class<?> objClass, Locale locale) {
        return getMessageForAny(Arrays.asList(objClass.getName(), objClass.getSimpleName()), objClass.getName(), locale);
    }

    @Override
    public String translateProperty(String objClass, String propertyName, Locale locale) {
        String propertyKey = objClass + "." + propertyName;
        return getMessage(propertyKey, new Object[]{}, propertyKey, locale);
    }

    @Override
    public String translateProperty(Class<?> objClass, String propertyName, Locale locale) {
        String propertyFQNKey = objClass.getName() + "." + propertyName;
        String propertySNKey = objClass.getSimpleName() + "." + propertyName;
        return getMessageForAny(Arrays.asList(propertyFQNKey, propertySNKey), propertyFQNKey, locale);
    }

    @Override
    public String getMessage(String code, Object[] args) throws NoSuchMessageI18nException {
        return getMessage(code, args, localeHolder.getCurrentLocale());
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage) {
        return getMessage(code, args, defaultMessage, localeHolder.getCurrentLocale());
    }

    @Override
    public String getMessageForAny(List<String> codes, String defaultMessage) {
        return getMessageForAny(codes, defaultMessage, localeHolder.getCurrentLocale());
    }

    @Override
    public String translateClass(Class<?> objClass) {
        return translateClass(objClass, localeHolder.getCurrentLocale());
    }

    @Override
    public String translateProperty(String objClass, String propertyName) {
        return translateProperty(objClass, propertyName, localeHolder.getCurrentLocale());
    }

    @Override
    public String translateProperty(Class<?> objClass, String propertyName) {
        return translateProperty(objClass, propertyName, localeHolder.getCurrentLocale());
    }
}
