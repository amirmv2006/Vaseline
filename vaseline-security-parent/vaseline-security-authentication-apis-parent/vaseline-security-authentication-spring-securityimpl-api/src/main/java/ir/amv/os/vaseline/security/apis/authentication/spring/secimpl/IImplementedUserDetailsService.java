package ir.amv.os.vaseline.security.apis.authentication.spring.secimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseHasPasswordUserDto;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseUserDto;
import ir.amv.os.vaseline.security.apis.authentication.service.server.base.IBaseUserService;
import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IUserPermissionsProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedUserDetailsService
        extends UserDetailsService {

    IBaseUserService getBaseUserService();
    IUserPermissionsProvider getUserPermissionsProvider();

    @Override
    default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            IBaseUserDto user = getBaseUserService().loadUserByUsername(username);
            List<GrantedAuthority> authorities = getUserPermissionsProvider().getUserAuthorities(username);
            if (user instanceof IBaseHasPasswordUserDto) {
                IBaseHasPasswordUserDto vaselineBaseUserDto = (IBaseHasPasswordUserDto) user;
                return new User(user.getUsername(), vaselineBaseUserDto.getPassword(), authorities);
            } else {
                throw new UsernameNotFoundException("Can not get password for " + user);
            }
        } catch (BaseVaselineClientException e) {
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException(e.getMessage());
//            ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            currentRequestAttributes.getRequest().getSession(true).setAttribute("loginError", usernameNotFoundException);
            throw usernameNotFoundException;
        }
    }
}
