package ir.amv.os.vaseline.ws.rest.authorization.basic.osgi.exc;

import ir.amv.os.vaseline.security.authorization.basic.api.shared.VaselineClientAccessDeniedException;
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
