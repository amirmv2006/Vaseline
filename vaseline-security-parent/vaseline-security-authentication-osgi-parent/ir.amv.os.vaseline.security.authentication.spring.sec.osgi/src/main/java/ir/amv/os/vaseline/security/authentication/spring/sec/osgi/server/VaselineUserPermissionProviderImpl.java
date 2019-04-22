package ir.amv.os.vaseline.security.authentication.spring.sec.osgi.server;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.spring.sec.def.IDefaultUserPermissionProvider;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IUserPermissionsProvider.class
)
public class VaselineUserPermissionProviderImpl
        implements IUserPermissionsProvider, IDefaultUserPermissionProvider {
}
