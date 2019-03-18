package ir.amv.os.vaseline.ws.rest.secured.spring.osgi;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.ISpringApplicationContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                ContainerRequestFilter.class
        }
)
@Provider
public class VaselineRestSecurityFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest servletRequest;
    @Context
    HttpServletResponse servletResponse;
    @Context
    ServletContext servletContext;

    private ISpringApplicationContext springApplicationContext;

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        DelegatingFilterProxy delegatingFilterProxy = springApplicationContext.getBean(DelegatingFilterProxy.class, servletContext);
        delegatingFilterProxy.setServletContext(servletContext);
        AtomicBoolean authenticationSuccess = new AtomicBoolean(false);
        AtomicReference<Exception> cause = new AtomicReference<>(new Exception("Default Authentication Exception"));
        try {
            delegatingFilterProxy.doFilter(servletRequest, servletResponse, (servletRequest, servletResponse) ->
                    authenticationSuccess.set(true));
        } catch (Exception e) {
            cause.set(e);
        }
        if (!authenticationSuccess.get()) {
            Response.ResponseBuilder rb = Response.status(Response.Status.UNAUTHORIZED);
            if (servletResponse.getStatus() == 302) {
                rb.header(HttpHeaders.WWW_AUTHENTICATE, servletResponse.getHeader("Location"));
            }
            throw new WebApplicationException(rb.build());
        }
    }

    @Reference
    public void setSpringApplicationContext(final ISpringApplicationContext springApplicationContext) {
        this.springApplicationContext = springApplicationContext;
    }
}
