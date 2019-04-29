package ir.amv.os.vaseline.ws.rest.secured.oauth2.osgi;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Amir
 */
@Path("oauth")
public interface IOAuthService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    Response login(
            @FormParam("username") final String username,
            @FormParam("password") final String password,
            @FormParam("scope") final String scope);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validateToken")
    OAuthToken validateToken(
            @QueryParam("token") final String token,
            @QueryParam("state") final String state);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authCodeInit")
    Response authCodeInit(@QueryParam("scope") String scope);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authCodeCallback")
    OAuthToken authCodeCallback(
            @QueryParam("code") final String authCode,
            @QueryParam("state") final String state
            );
}