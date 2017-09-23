package ir.amv.os.vaseline.security.authentication.spring.impl.config.userdetailsservice;

import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.security.authentication.spring.impl.config.permissions.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.BaseUserEntity;
import ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.IBaseUserApi;
import ir.amv.os.vaseline.security.authentication.spring.impl.shared.dto.model.user.BaseUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;

/**
 * Created by AMV on 2/28/2016.
 */
public class VaselineUserDetailsService
        implements UserDetailsService {

    @Autowired
    IBaseUserApi baseUserApi;

    @Autowired
    Environment environment;

    @Autowired
    IUserPermissionsProvider userPermissionsProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUserDto baseUserDto = new BaseUserDto();
        baseUserDto.setUsername(username);
        try {
            List<BaseUserEntity> searchByExample = baseUserApi.searchByExample(
                    baseUserDto, new PagingDto(null, 0, 1));
            if (searchByExample == null || searchByExample.isEmpty()) {
                String message = "Specified User Not Found!!!";
//                saveLogMessage(userName, message, LogLevel.error);
                throw new UsernameNotFoundException(message);
            }
            BaseUserEntity user = searchByExample.get(0);
            if (!user.getUsername().equals(username)) {
                String message = "Specified User Not Found!!!";
//                saveLogMessage(userName, message, LogLevel.error);
                throw new UsernameNotFoundException(message);
            }
            if (user.getEnabled() != null && !user.getEnabled()) {
                String message = "User " + username + " is disabled";
//                saveLogMessage(userName, message, LogLevel.error);
                throw new DisabledException(message);
            }
            Integer maxUserNotLoginedDays = environment.getProperty("sec.user.authenticate.max.user.not.login", Integer.TYPE, 30);
            if (maxUserNotLoginedDays != -1) {
                Date today = DateUtil.newDate();
                Date lastLoginTime = user.getLastLoginTime();
                if (lastLoginTime != null) {
                    int userNotLogined = DateUtil.subtractInDays(lastLoginTime, today);
                    if (userNotLogined > maxUserNotLoginedDays) {
                        String message = "User has not logged in in " + userNotLogined + " days and is expired";
//                        saveLogMessage(userName, message, LogLevel.error);
                        throw new CredentialsExpiredException(message);
                    }
                }
            }
            List<SimpleGrantedAuthority> authorities = userPermissionsProvider
                    .getUserAuthorities(username);

            String message = "Authentication Success";
//            saveLogMessage(userName, message, LogLevel.info);
            user.setLastLoginTime(DateUtil.newDate());
            baseUserApi.update(user);
            return new User(user.getUsername(), user.getPassword(), authorities);
        } catch (BaseVaselineServerException e) {
            String message = "نام کاربری/گذرواژه اشتباه است";
//            saveLogMessage(userName, message, LogLevel.error);
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException(message);
            ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            currentRequestAttributes.getRequest().getSession(true).setAttribute("loginError", usernameNotFoundException);
            throw usernameNotFoundException;
        }
    }
}
