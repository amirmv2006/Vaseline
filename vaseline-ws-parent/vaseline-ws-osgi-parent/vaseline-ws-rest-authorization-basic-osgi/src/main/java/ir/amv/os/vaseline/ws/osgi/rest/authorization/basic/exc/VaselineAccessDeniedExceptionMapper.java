package ir.amv.os.vaseline.ws.osgi.rest.authorization.basic.exc;

import ir.amv.os.vaseline.security.apis.authorization.basic.shared.VaselineClientAccessDeniedException;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = ExceptionMapper.class
)
@Provider
public class VaselineAccessDeniedExceptionMapper
        implements ExceptionMapper<VaselineClientAccessDeniedException> {
    @Override
    public Response toResponse(final VaselineClientAccessDeniedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
    }
}
