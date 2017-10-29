package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.permissions.defimpl;

import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.permissions.IUserPermissionsProvider;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserPermissionsProviderImpl implements IUserPermissionsProvider {

	@Override
	public List<GrantedAuthority> getUserAuthorities(String username) {
		return new ArrayList<>();
	}

}
