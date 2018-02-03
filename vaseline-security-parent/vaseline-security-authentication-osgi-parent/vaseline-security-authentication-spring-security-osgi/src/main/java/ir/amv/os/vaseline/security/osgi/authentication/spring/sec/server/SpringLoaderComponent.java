package ir.amv.os.vaseline.security.osgi.authentication.spring.sec.server;

import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IHttpSecurityConfigurer;
import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.ISecuredServletContextHolder;
import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.ISpringApplicationContext;
import ir.amv.os.vaseline.security.apis.authentication.spring.sec.config.IUserPermissionsProvider;
import ir.amv.os.vaseline.security.osgi.authentication.spring.sec.config.SpringOsgiBridge;
import ir.amv.os.vaseline.security.osgi.authentication.spring.sec.config.VaselineSpringSecurityConfig;
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
    private ISecuredServletContextHolder servletContextHolder;
    private UserDetailsService userDetailsService;
    private List<IHttpSecurityConfigurer> securityConfigurerList = new ArrayList<>();
    private IUserPermissionsProvider userPermissionsProvider;

    private void loadSpring() {
        initBridge();
        context = new AnnotationConfigWebApplicationContext();
        context.setClassLoader(getClass().getClassLoader());
        context.register(VaselineSpringSecurityConfig.class);
        context.setServletContext(servletContextHolder.getServletContext());
        context.refresh();
    }

    private void initBridge() {
        SpringOsgiBridge.getInstance().setSecurityConfigurerList(securityConfigurerList);
        SpringOsgiBridge.getInstance().setUserDetailsService(userDetailsService);
        SpringOsgiBridge.getInstance().setUserPermissionsProvider(userPermissionsProvider);
    }

    @Reference
    public void setServletContextHolder(final ISecuredServletContextHolder servletContextHolder) {
        this.servletContextHolder = servletContextHolder;
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
    public synchronized <T> T getBean(final Class<T> tClass) {
        if (context == null) {
            loadSpring();
        }
        return context.getBean(tClass);
    }

    @Override
    public synchronized void destroy() {
        context.close();
        context = null;
    }
}
