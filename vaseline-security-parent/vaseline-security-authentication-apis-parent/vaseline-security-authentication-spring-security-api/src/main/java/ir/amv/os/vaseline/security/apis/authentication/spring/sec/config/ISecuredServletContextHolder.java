package ir.amv.os.vaseline.security.apis.authentication.spring.sec.config;

import javax.servlet.ServletContext;

/**
 * @author Amir
 */
public interface ISecuredServletContextHolder {

    ServletContext getServletContext();
}
