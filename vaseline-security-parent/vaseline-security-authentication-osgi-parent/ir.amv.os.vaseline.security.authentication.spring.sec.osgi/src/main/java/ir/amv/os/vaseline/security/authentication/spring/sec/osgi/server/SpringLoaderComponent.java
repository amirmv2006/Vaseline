package ir.amv.os.vaseline.security.authentication.spring.sec.osgi.server;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IHttpSecurityConfigurer;
import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.ISpringApplicationContext;
import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.authentication.spring.sec.osgi.config.SpringOsgiBridge;
import ir.amv.os.vaseline.security.authentication.spring.sec.osgi.config.VaselineSpringSecurityConfig;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
@Component(
        service = ISpringApplicationContext.class
)
public class SpringLoaderComponent implements ISpringApplicationContext {

    private AnnotationConfigWebApplicationContext context;
    private UserDetailsService userDetailsService;
    private List<IHttpSecurityConfigurer> securityConfigurerList = new ArrayList<>();
    private IUserPermissionsProvider userPermissionsProvider;

    private void loadSpring(final ServletContext servletContext) {
        initBridge();
        context = new AnnotationConfigWebApplicationContext();
        context.setClassLoader(getClass().getClassLoader());
        context.register(VaselineSpringSecurityConfig.class);
        context.setServletContext(servletContext);
        context.refresh();
    }

    private void initBridge() {
        SpringOsgiBridge.getInstance().setSecurityConfigurerList(securityConfigurerList);
        SpringOsgiBridge.getInstance().setUserDetailsService(userDetailsService);
        SpringOsgiBridge.getInstance().setUserPermissionsProvider(userPermissionsProvider);
    }

    @Reference
    public void setUserDetailsService(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Reference
    public void setUserPermissionsProvider(final IUserPermissionsProvider userPermissionsProvider) {
        this.userPermissionsProvider = userPermissionsProvider;
    }

    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE
    )
    public void addSecurityConfigurerList(final IHttpSecurityConfigurer securityConfigurer) {
        securityConfigurerList.add(securityConfigurer);
    }

    @Override
    public synchronized <T> T getBean(final Class<T> tClass, final ServletContext servletContext) {
        if (context == null) {
            loadSpring(servletContext);
        }
        return context.getBean(tClass);
    }

    @Override
    public synchronized void destroy() {
        context.close();
        context = null;
    }
}
