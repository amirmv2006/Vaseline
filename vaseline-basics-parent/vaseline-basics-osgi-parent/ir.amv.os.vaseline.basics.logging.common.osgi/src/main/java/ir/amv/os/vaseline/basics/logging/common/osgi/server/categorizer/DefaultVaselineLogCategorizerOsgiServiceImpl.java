package ir.amv.os.vaseline.basics.logging.common.osgi.server.categorizer;

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.def.server.categorizer.IDefaultVaselineLogCategorizer;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineLogCategorizer.class
)
public class DefaultVaselineLogCategorizerOsgiServiceImpl
        implements IVaselineLogCategorizer, IDefaultVaselineLogCategorizer {

}
