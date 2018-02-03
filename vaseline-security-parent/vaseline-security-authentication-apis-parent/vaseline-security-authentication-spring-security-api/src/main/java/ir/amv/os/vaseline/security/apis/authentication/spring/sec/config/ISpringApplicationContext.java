package ir.amv.os.vaseline.security.apis.authentication.spring.sec.config;

import javax.servlet.ServletContext;

/**
 * @author Amir
 */
public interface ISpringApplicationContext {

    <T> T getBean(Class<T> tClass, final ServletContext servletContext);

    void destroy();
}
