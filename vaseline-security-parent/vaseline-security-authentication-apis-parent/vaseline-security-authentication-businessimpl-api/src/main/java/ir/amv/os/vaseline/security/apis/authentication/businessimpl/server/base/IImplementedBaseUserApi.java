package ir.amv.os.vaseline.security.apis.authentication.businessimpl.server.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.apis.audit.basic.server.IAuditApi;
import ir.amv.os.vaseline.security.apis.authentication.business.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

import javax.transaction.Transactional;
import java.lang.reflect.Method;

/**
 * @author Amir
 */
public interface IImplementedBaseUserApi<U extends IBaseUserEntity, Dao extends IBaseUserDao<U>>
        extends IBaseImplementedReadOnlyApi<U, Long, Dao>, IBaseUserApi<U> {

    IAuditApi getAuditApi();

    @Override
    @Transactional
    @VaselineBuinessMetadata(
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    )
    default U loadUserByUsername(String username) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<U>)() -> {
            U user = getDao().getUserByUsername(username);
            postGet(user);
            return user;
        });
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
    @VaselineBuinessMetadata(
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    )
    default void authenticationSuccessful(String username) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>)() -> {
            getAuditApi().auditBusinessAction(username, "Authentication", "SUCCESS", null);
            return null;
        });
    }

    @Override
    @VaselineBuinessMetadata(
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    )
    default void authenticationFailure(String username) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<U>)() -> {
            getAuditApi().auditBusinessAction(username, "Authentication", "FAIL", null);
            return null;
        });

    }
}
