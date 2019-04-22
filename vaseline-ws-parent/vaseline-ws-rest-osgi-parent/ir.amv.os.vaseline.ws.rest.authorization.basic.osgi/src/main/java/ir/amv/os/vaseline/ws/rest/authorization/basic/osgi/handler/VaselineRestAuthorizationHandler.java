package ir.amv.os.vaseline.ws.rest.authorization.basic.osgi.handler;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.tx.api.ITransactionTemplateApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.INoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAuthorizationException;
import ir.amv.os.vaseline.ws.rest.secured.oauth2.osgi.authorization.IRestPartialAuthorizationHandler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.security.Principal;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IRestPartialAuthorizationHandler.class
        }
)
public class VaselineRestAuthorizationHandler
        implements IRestPartialAuthorizationHandler {
    private INoAuthAuthorizationApi noAuthAuthorizationApi;
    private ITransactionTemplateApi transactionTemplateApi;

    @Override
    public boolean isUserInRole(final Principal user, final String role) {
        try {
            return transactionTemplateApi.doInATransaction(
                    "VaselineRestAuthorizationHandler",
                    () -> {
                        try {
                            noAuthAuthorizationApi.checkAuthorization(user.getName(), role);
                            return true;
                        } catch (VaselineAuthorizationException e) {
                            return false;
                        }
                    });
        } catch (BaseVaselineServerException e) {
            return false;
        }
    }

    @Reference
    public void setNoAuthAuthorizationApi(final INoAuthAuthorizationApi noAuthAuthorizationApi) {
        this.noAuthAuthorizationApi = noAuthAuthorizationApi;
    }

    @Reference
    public void setTransactionTemplateApi(final ITransactionTemplateApi transactionTemplateApi) {
        this.transactionTemplateApi = transactionTemplateApi;
    }
}
