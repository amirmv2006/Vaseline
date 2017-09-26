package ir.amv.os.vaseline.security.authentication.spring.impl.config.permissions.impl;

import ir.amv.os.vaseline.security.authentication.spring.impl.config.permissions.IUserPermissionsProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class BasicUserPermissionsProvider implements IUserPermissionsProvider {

	@Override
	public List<SimpleGrantedAuthority> getUserAuthorities(String username) {
		return new ArrayList<SimpleGrantedAuthority>();
	}

}
