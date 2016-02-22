package ir.amv.os.vaseline.base.i18n.server.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by AMV on 2/10/2016.
 */
@Component
public class VaselineI18nResolver {

    private MessageSource messageSource;

    public String translate(List<String> possibleKeys) {
        for (String possibleKey : possibleKeys) {
            try {
                return translate(possibleKey);
            } catch (NoSuchMessageException e) {
            }
        }
        return possibleKeys.get(0);
    }

    public String translateProperty(String objClass, String propertyName) {
        String propertyKey = objClass + "." + propertyName;
        return translate(propertyKey);
    }

    public String translateProperty(Class<?> objClass, String propertyName) {
        String propertyFQNKey = objClass.getName() + "." + propertyName;
        String propertySNKey = objClass.getSimpleName() + "." + propertyName;
        return translate(Arrays.asList(propertyFQNKey, propertySNKey));
    }

    public String translateClass(Class<?> objClass) {
        return translate(Arrays.asList(objClass.getName(), objClass.getSimpleName()));
    }

    public String translate(String string) throws NoSuchMessageException {
        Locale locale = getCurrentLocale();
        String dtoTranslated = null;
        try {
            dtoTranslated = messageSource.getMessage(string,
                    new Object[]{}, locale);
        } catch (NoSuchMessageException e) {
            dtoTranslated = string;
        }
        return dtoTranslated;
    }

    protected Locale getCurrentLocale() {
        return new Locale("fa")/* LocaleContextHolder.getLocale() */;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
