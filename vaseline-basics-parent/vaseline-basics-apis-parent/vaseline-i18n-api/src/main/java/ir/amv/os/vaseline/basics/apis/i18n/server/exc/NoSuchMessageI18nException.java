package ir.amv.os.vaseline.basics.apis.i18n.server.exc;

/**
 * Created by AMV on 10/1/2017.
 */
public class NoSuchMessageI18nException
        extends Exception {

    public NoSuchMessageI18nException() {
    }

    public NoSuchMessageI18nException(String message) {
        super(message);
    }
}
