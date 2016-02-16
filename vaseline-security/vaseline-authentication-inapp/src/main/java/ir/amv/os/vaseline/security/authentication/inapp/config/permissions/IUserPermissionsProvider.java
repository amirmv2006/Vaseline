package ir.amv.os.vaseline.security.authentication.inapp.config.permissions;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface IUserPermissionsProvider {


	List<SimpleGrantedAuthority> getUserAuthorities(String username);
}
