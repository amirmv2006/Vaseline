package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.config;

import ir.amv.os.vaseline.basics.osgi.base.conf.IBaseConfig;

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
