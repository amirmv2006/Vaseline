package ir.amv.os.vaseline.security.authentication.spring.sec.def;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IUserPermissionsProvider;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultUserPermissionProvider
        extends IUserPermissionsProvider {

    @Override
    default List<GrantedAuthority> getUserAuthorities(String username) {
        return new ArrayList<>();
    }
}
