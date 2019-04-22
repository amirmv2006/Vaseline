package ir.amv.os.vaseline.basics.i18n.api.server.locale;

import java.util.Locale;

/**
 * Created by AMV on 10/1/2017.
 */
public interface IVaselineLocaleHolder {
    Locale getCurrentLocale();
    void setCurrentLocale(Locale locale);
}
