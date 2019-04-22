package ir.amv.os.vaseline.basics.i18n.osgi;

import ir.amv.os.vaseline.basics.i18n.api.server.file.resolver.IVaselineI18nFileProvider;
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
