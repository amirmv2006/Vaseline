/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ir.amv.os.vaseline.security.authentication.oauth2.config;

import ir.amv.os.vaseline.security.authentication.spring.impl.config.VaselineAuthenticationImplConfig;
import ir.amv.os.vaseline.security.authentication.spring.impl.config.external.IHttpSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Rob Winch
 * 
 */
@Configuration
@Import({
        VaselineAuthenticationImplConfig.class,
        OAuth2ServerConfig.ResourceServerConfiguration.class,
        OAuth2ServerConfig.AuthorizationServerConfiguration.class
})
public class OAuth2ServerConfig {

	private static final String APP_RESOURCE_ID = "sparklr";

    @Bean
    public IHttpSecurityConfigurer inAppConfigurer() {
        return new IHttpSecurityConfigurer() {
            @Override
            public Integer priority() {
                return 10;
            }

            @Override
            public HttpSecurity configure(HttpSecurity http) {
                try {
                    http.formLogin()
                            .loginPage("/login.html")
                            .loginProcessingUrl("/login.do")
                            //                .successHandler(authenticationSuccessRedirect())
                            //				.defaultSuccessUrl("/login.success", true)
                            //	.failureUrl("/login?err=1")
                            //                .failureHandler(authenticationFailureRedirect())
                            .usernameParameter("username")
                            .passwordParameter("password")

                            .and()

                            // This is where the logout page and process is configured. The
                            // logout-url is the URL to send
                            // the user to in order to logout, the logout-success-url is
                            // where they are taken if the logout
                            // is successful, and the delete-cookies and invalidate-session
                            // make sure that we clean up after logout
                            .logout()
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/login.html")
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true);
                    return http;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                return registry.antMatchers("/oauth/**").permitAll();
            }
        };
    }

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId(APP_RESOURCE_ID).stateless(false);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				// Since we want the protected resources to be accessible in the UI as well we need 
				// session creation to be allowed (it's disabled by default in 2.0.6)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.and()
					.requestMatchers().antMatchers("/cxf/rest/**", "/cxf/soap/**")
					.and()
					.authorizeRequests()
					.antMatchers("/cxf/rest/**", "/cxf/soap/**").access("#oauth2.hasScope('app') or (!#oauth2.isOAuth() and isAuthenticated())");
			// @formatter:on
		}

	}

	@Configuration
    @Import(Stuff.class)
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private TokenStore tokenStore;

		@Autowired
		private UserApprovalHandler userApprovalHandler;

		@Autowired
//		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

//		@Value("${tonr.redirect:http://localhost:8080/tonr2/sparklr/redirect}")
//		private String tonrRedirectUri;

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

			// @formatter:off
			clients.inMemory()
//					.withClient("mobile")
//			 			.resourceIds(APP_RESOURCE_ID)
//			 			.authorizedGrantTypes("authorization_code", "client credentials")
//			 			.authorities("IS_AUTHENTICATED")
//			 			.scopes("app")
//			 			.secret("secret")
//			 		.and()
//			 		.withClient("tonr-with-redirect")
//			 			.resourceIds(APP_RESOURCE_ID)
//			 			.authorizedGrantTypes("authorization_code", "implicit")
//			 			.authorities("ROLE_CLIENT")
//			 			.scopes("read", "write")
//			 			.secret("secret")
//			 			.redirectUris(tonrRedirectUri)
//			 		.and()
//		 		    .withClient("my-client-with-registered-redirect")
//	 			        .resourceIds(APP_RESOURCE_ID)
//	 			        .authorizedGrantTypes("authorization_code", "client_credentials")
//	 			        .authorities("ROLE_CLIENT")
//	 			        .scopes("read", "trust")
//	 			        .redirectUris("http://anywhere?key=value")
//		 		    .and()
	 		        .withClient("my-trusted-client")
 			            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
 			            .authorities("IS_AUTHENTICATED")
 			            .scopes("app")
                        .secret("secret")
 			            .accessTokenValiditySeconds(60)
//		 		    .and()
//	 		        .withClient("my-trusted-client-with-secret")
// 			            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
// 			            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
// 			            .scopes("read", "write", "trust")
// 			            .secret("somesecret")
//	 		        .and()
// 		            .withClient("my-less-trusted-client")
//			            .authorizedGrantTypes("authorization_code", "implicit")
//			            .authorities("ROLE_CLIENT")
//			            .scopes("read", "write", "trust")
//     		        .and()
//		            .withClient("my-less-trusted-autoapprove-client")
//		                .authorizedGrantTypes("implicit")
//		                .authorities("ROLE_CLIENT")
//		                .scopes("read", "write", "trust")
//		                .autoApprove(true)
					;
			// @formatter:on
		}

		@Bean
		public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
					.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//			oauthServer.realm("sample-project/client");
            oauthServer.allowFormAuthenticationForClients();
		}

	}

	@Configuration
	protected static class Stuff {

		@Autowired
		private ClientDetailsService clientDetailsService;

		@Autowired
		private TokenStore tokenStore;

		@Bean
		public ApprovalStore approvalStore() throws Exception {
			TokenApprovalStore store = new TokenApprovalStore();
			store.setTokenStore(tokenStore);
			return store;
		}

		@Bean
		@Lazy
		@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
		public SparklrUserApprovalHandler userApprovalHandler() throws Exception {
			SparklrUserApprovalHandler handler = new SparklrUserApprovalHandler();
			handler.setApprovalStore(approvalStore());
			handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
			handler.setClientDetailsService(clientDetailsService);
			handler.setUseApprovalStore(true);
			return handler;
		}
	}

}
