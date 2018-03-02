package ir.amv.os.vaseline.security.apis.authentication.spring.secimpl;

import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IUserPermissionsProvider;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedUserPermissionProvider
        extends IUserPermissionsProvider {

    @Override
    default List<GrantedAuthority> getUserAuthorities(String username) {
        return new ArrayList<>();
    }
}
