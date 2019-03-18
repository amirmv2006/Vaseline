package ir.amv.os.vaseline.ws.rest.secured.oauth2.osgi.config;

import ir.amv.os.vaseline.basics.base.osgi.conf.IBaseConfig;

/**
 * @author Amir
 */
public interface IVaselineOAuthConfig extends IBaseConfig {

    String AUTHENTICATION_CACHE_GROUP = "vaseline-oauth";
    String TOKEN_CACHE_NAME = "VaselineTokenCache";
    String STATE_CACHE_NAME = "VaselineTokenCache";

    String getTokenUrl();
    String getClientId();
    String getClientSecret();
    String getCheckTokenUrl();

    String getAuthorizeUrl();
}
