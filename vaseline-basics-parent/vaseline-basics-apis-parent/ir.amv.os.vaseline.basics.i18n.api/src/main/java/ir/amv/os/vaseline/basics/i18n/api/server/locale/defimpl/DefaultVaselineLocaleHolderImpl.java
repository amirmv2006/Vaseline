package ir.amv.os.vaseline.basics.i18n.api.server.locale.defimpl;

import ir.amv.os.vaseline.basics.i18n.api.server.locale.IVaselineLocaleHolder;

import java.util.Locale;

/**
 * Created by AMV on 10/1/2017.
 */
public class DefaultVaselineLocaleHolderImpl
        implements IVaselineLocaleHolder {
    private Locale currentLocale;

    @Override
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    @Override
    public void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }
}
