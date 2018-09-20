package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public class OAuthToken implements Serializable {

    private Long exp;
    private String user_name;
    private List<String> authorities;
    private String client_id;
    private List<String> scope;

    public Long getExp() {
        return exp;
    }

    public void setExp(final Long exp) {
        this.exp = exp;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(final String user_name) {
        this.user_name = user_name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final List<String> authorities) {
        this.authorities = authorities;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(final String client_id) {
        this.client_id = client_id;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(final List<String> scope) {
        this.scope = scope;
    }
}
