package ir.amv.os.vaseline.basics.apis.i18n.server.locale;

import java.util.Locale;

/**
 * Created by AMV on 10/1/2017.
 */
public interface IVaselineLocaleHolder {
    Locale getCurrentLocale();
    void setCurrentLocale(Locale locale);
}
