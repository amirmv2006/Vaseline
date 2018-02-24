package ir.amv.os.vaseline.security.osgi.authentication.oauth2.jersey.client;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.Map;

/**
 * @author Amir
 */
public class Test {

    public static void main(String[] args) {
        JerseyClient cb = JerseyClientBuilder.createClient();
        JerseyWebTarget target = cb.target("http://localhost:8888/auth/oauth/token");
        byte[] encoded = Base64.getEncoder().encode("SampleClientId:secret".getBytes());
        Invocation.Builder invocationBuilder
                = target.request(MediaType.APPLICATION_JSON).header("Authorization","Basic " + new String
                (encoded));
        MultivaluedMap<String, String> mvm = new MultivaluedHashMap<>();
        mvm.add("grant_type","password");
        mvm.add("username","amir");
        mvm.add("password","amir");
        mvm.add("scope","user_info");
        Response post = invocationBuilder.post(Entity.form(new Form(mvm)));
        MultivaluedMap<String, Object> headers = post.getHeaders();
        headers.forEach((k,v)-> System.out.println(k + " = " + v));
        Map entity = post.readEntity(Map.class);
        entity.forEach((k,v)-> System.out.println(k + " = " + v));
    }
}
