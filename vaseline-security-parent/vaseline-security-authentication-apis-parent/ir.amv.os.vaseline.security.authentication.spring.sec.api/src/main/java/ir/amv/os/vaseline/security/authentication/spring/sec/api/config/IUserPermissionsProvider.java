package ir.amv.os.vaseline.security.authentication.spring.sec.api.config;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IUserPermissionsProvider {


	List<GrantedAuthority> getUserAuthorities(String username);
}
