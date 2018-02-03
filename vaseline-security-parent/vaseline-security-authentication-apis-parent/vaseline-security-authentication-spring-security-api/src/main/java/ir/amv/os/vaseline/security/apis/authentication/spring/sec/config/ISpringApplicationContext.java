package ir.amv.os.vaseline.security.apis.authentication.spring.sec.config;

/**
 * @author Amir
 */
public interface ISpringApplicationContext {

    <T> T getBean(Class<T> tClass);

    void destroy();
}
