package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.permissions;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IUserPermissionsProvider {


	List<GrantedAuthority> getUserAuthorities(String username);
}
