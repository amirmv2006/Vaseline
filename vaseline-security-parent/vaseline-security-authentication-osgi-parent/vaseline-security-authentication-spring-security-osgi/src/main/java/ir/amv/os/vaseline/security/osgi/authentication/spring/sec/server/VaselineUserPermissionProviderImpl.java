package ir.amv.os.vaseline.security.osgi.authentication.spring.sec.server;

import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.apis.authentication.spring.secimpl.IImplementedUserPermissionProvider;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IUserPermissionsProvider.class
)
public class VaselineUserPermissionProviderImpl
        implements IUserPermissionsProvider, IImplementedUserPermissionProvider {
}
