package ir.amv.os.vaseline.security.authentication.business.def.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.security.audit.basic.api.server.IAuditApi;
import ir.amv.os.vaseline.security.authentication.business.api.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserEntity;

import javax.transaction.Transactional;

/**
 * @author Amir
 */
public interface IDefaultBaseUserApi<U extends IBaseUserEntity, Dao extends IBaseUserDao<U>>
        extends IDefaultReadOnlyApi<Long, U, Dao>, IBaseUserApi<U> {

    IAuditApi getAuditApi();

    @Override
    @Transactional
    default U loadUserByUsername(String username) throws BaseVaselineServerException {
        U user = getDao().getUserByUsername(username);
        postGet(user);
        return user;
//        try {
//            IBaseUserDto user = baseUserApi.loadUserByUsername(username);
//            if (user == null) {
//                String message = "Specified User Not Found!!!";
////                saveLogMessage(userName, message, LogLevel.error);
//                throw new UsernameNotFoundException(message);
//            }
//            if (user.getEnabled() != null && !user.getEnabled()) {
//                String message = "User " + username + " is disabled";
////                saveLogMessage(userName, message, LogLevel.error);
//                throw new DisabledException(message);
//            }
//            Integer maxUserNotLoginedDays = environment.getProperty("sec.user.authenticate.max.user.not.login", Integer.TYPE, 30);
//            if (maxUserNotLoginedDays != -1) {
//                Date today = DateUtil.newDate();
//                Date lastLoginTime = user.getLastLoginTime();
//                if (lastLoginTime != null) {
//                    int userNotLogined = DateUtil.subtractInDays(lastLoginTime, today);
//                    if (userNotLogined > maxUserNotLoginedDays) {
//                        String message = "User has not logged in in " + userNotLogined + " days and is expired";
////                        saveLogMessage(userName, message, LogLevel.error);
//                        throw new CredentialsExpiredException(message);
//                    }
//                }
//            }
//            List<SimpleGrantedAuthority> authorities = userPermissionsProvider
//                    .getUserAuthorities(username);
//
//            String message = "Authentication Success";
////            saveLogMessage(userName, message, LogLevel.info);
//            user.setLastLoginTime(DateUtil.newDate());
//            baseUserApi.update(user);
//            return new User(user.getUsername(), user.getPassword(), authorities);
//        } catch (BaseVaselineClientException e) {
//            String message = "نام کاربری/گذرواژه اشتباه است";
////            saveLogMessage(userName, message, LogLevel.error);
//            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException(message);
//            ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            currentRequestAttributes.getRequest().getSession(true).setAttribute("loginError", usernameNotFoundException);
//            throw usernameNotFoundException;
//        }
    }

    @Override
    default void authenticationSuccessful(String username) throws BaseVaselineServerException {
        getAuditApi().auditBusinessAction(username, "Authentication", "SUCCESS", null);
    }

    @Override
    default void authenticationFailure(String username) throws BaseVaselineServerException {
        getAuditApi().auditBusinessAction(username, "Authentication", "FAIL", null);
    }
}
