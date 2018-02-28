package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.config;

/**
 * @author Amir
 */
public interface IVaselineOAuthConfig {

    String AUTHENTICATION_CACHE_GROUP = "vaseline-oauth";
    String TOKEN_CACHE_NAME = "VaselineTokenCache";
    String STATE_CACHE_NAME = "VaselineTokenCache";

    String getTokenUrl();
    String getClientId();
    String getClientSecret();
    String getCheckTokenUrl();

    String getAuthorizeUrl();
}
