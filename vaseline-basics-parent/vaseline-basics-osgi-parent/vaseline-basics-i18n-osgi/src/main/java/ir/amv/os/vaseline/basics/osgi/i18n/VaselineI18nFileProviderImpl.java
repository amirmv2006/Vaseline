package ir.amv.os.vaseline.basics.osgi.i18n;

import ir.amv.os.vaseline.basics.apis.i18n.server.file.resolver.IVaselineI18nFileProvider;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineI18nFileProvider.class
)
public class VaselineI18nFileProviderImpl
        implements IVaselineI18nFileProvider {
    @Override
    public String fileBaseName() {
        return "vaseline-default-i18n";
    }

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }
}
