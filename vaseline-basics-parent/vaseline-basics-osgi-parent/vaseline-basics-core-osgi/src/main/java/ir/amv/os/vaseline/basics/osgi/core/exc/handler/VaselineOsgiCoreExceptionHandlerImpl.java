package ir.amv.os.vaseline.basics.osgi.core.exc.handler;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.defimpl.CoreExceptionHandlerImpl;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = ICoreExceptionHandler.class
)
public class VaselineOsgiCoreExceptionHandlerImpl
        extends CoreExceptionHandlerImpl
        implements ICoreExceptionHandler {
}
