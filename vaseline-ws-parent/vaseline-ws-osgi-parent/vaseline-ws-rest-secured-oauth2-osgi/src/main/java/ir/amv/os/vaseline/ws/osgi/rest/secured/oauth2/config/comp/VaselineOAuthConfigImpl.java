package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.config.comp;

import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.config.IVaselineOAuthConfig;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.annotations.Component;

import java.util.Dictionary;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        property = Constants.SERVICE_PID + "=ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2"
)
public class VaselineOAuthConfigImpl
        implements ManagedService, IVaselineOAuthConfig {
    private String tokenUrl;
    private String authorizeUrl;
    private String clientId;
    private String clientSecret;
    private String checkTokenUrl;

    @Override
    public void updated(final Dictionary<String, ?> properties) throws ConfigurationException {
        tokenUrl = (String) properties.get("tokenUrl");
        clientId = (String) properties.get("clientId");
        clientSecret = (String) properties.get("clientSecret");
        checkTokenUrl = (String) properties.get("checkTokenUrl");
        authorizeUrl = (String) properties.get("authorizeUrl");
    }

    @Override
    public String getTokenUrl() {
        return tokenUrl;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public String getCheckTokenUrl() {
        return checkTokenUrl;
    }

    @Override
    public String getAuthorizeUrl() {
        return authorizeUrl;
    }
}
