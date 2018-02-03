package ir.amv.os.vaseline.ws.osgi.rest.secured;

import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.ISpringApplicationContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

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
        try {
            delegatingFilterProxy.doFilter(servletRequest, servletResponse, (servletRequest, servletResponse) -> System.out.println("Done!"));
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Reference
    public void setSpringApplicationContext(final ISpringApplicationContext springApplicationContext) {
        this.springApplicationContext = springApplicationContext;
    }
}
