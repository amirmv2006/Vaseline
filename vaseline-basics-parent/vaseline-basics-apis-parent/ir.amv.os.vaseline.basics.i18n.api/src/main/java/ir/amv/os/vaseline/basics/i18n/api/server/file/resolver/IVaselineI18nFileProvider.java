package ir.amv.os.vaseline.basics.i18n.api.server.file.resolver;

/**
 * Created by AMV on 2/10/2016.
 */
public interface IVaselineI18nFileProvider {

    String fileBaseName();

    ClassLoader getClassLoader();
}
