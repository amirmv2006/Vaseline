package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.baseapi;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.base.IBaseImplementedApi;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.server.util.SpringSecurityAuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by AMV on 2/17/2016.
 */
@Component
public class VaselineAuthenticationApiSpringSecurityImpl
        extends ProxyAwareImpl
        implements IBaseImplementedApi, IAuthenticationApi {
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public String getCurrentUsername() throws BaseVaselineServerException {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = SpringSecurityAuthenticationUtil.getUsernameFromAuthentication(authentication);
        if (username != null) {
            return username;
        }
        throw new BaseVaselineServerException("current user is null.");
    }

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Autowired
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }
}
