package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.config;

/**
 * @author Amir
 */
public interface IVaselineOAuthConfig {

    String TOKEN_CACHE_NAME = "VaselineTokenCache";

    String getTokenUrl();
    String getClientId();
    String getClientSecret();
    String getCheckTokenUrl();
}
