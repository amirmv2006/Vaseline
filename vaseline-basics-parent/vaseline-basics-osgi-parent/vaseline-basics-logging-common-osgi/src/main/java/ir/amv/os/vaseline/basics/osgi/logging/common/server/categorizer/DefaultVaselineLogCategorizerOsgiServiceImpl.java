package ir.amv.os.vaseline.basics.osgi.logging.common.server.categorizer;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.loggingimpl.server.categorizer.IImplementedVaselineLogCategorizer;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineLogCategorizer.class
)
public class DefaultVaselineLogCategorizerOsgiServiceImpl
        implements IVaselineLogCategorizer, IImplementedVaselineLogCategorizer {

}
