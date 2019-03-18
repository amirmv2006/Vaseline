package ir.amv.os.vaseline.basics.i18n.osgi;

import ir.amv.os.vaseline.basics.i18n.api.server.exc.NoSuchMessageI18nException;
import ir.amv.os.vaseline.basics.i18n.api.server.file.resolver.IVaselineI18nFileProvider;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.defimpl.BaseVaselineMessageTranslatorImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineMessageTranslator.class
)
public class VaselineMessageTranslatorImpl
        extends BaseVaselineMessageTranslatorImpl
        implements IVaselineMessageTranslator {

    private List<IVaselineI18nFileProvider> fileProviders;

    public VaselineMessageTranslatorImpl() {
        fileProviders = new ArrayList<>();
    }

    @Override
    public String getMessage(final String code, final Object[] args, final Locale locale) throws NoSuchMessageI18nException {
        for (IVaselineI18nFileProvider fileProvider : fileProviders) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(fileProvider.fileBaseName(), locale,
                    fileProvider.getClassLoader());
            if (resourceBundle.containsKey(code)) {
                return resourceBundle.getString(code);
            }
        }
        return null;
    }

    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE
    )
    public void addVaselineI18nFileProvider(IVaselineI18nFileProvider fileProvider) {
        fileProviders.add(fileProvider);
    }
}
