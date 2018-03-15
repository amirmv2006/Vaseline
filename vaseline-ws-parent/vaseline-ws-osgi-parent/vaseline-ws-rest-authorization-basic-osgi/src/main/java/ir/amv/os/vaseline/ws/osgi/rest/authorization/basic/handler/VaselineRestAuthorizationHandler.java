package ir.amv.os.vaseline.ws.osgi.rest.authorization.basic.handler;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.tx.ITransactionTemplateApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.INoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.authorization.IRestPartialAuthorizationHandler;
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
                    "ir.amv.os.vaseline.ws.osgi.rest.authorization.basic.handler.VaselineRestAuthorizationHandler",
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
