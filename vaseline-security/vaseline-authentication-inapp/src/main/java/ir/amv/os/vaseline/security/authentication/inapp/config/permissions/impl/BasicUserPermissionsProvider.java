package ir.amv.os.vaseline.security.authentication.inapp.config.permissions.impl;

import java.util.ArrayList;
import java.util.List;

import ir.amv.os.vaseline.security.authentication.inapp.config.permissions.IUserPermissionsProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class BasicUserPermissionsProvider implements IUserPermissionsProvider {

	@Override
	public List<SimpleGrantedAuthority> getUserAuthorities(String username) {
		return new ArrayList<SimpleGrantedAuthority>();
	}

}
