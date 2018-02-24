package ir.amv.os.vaseline.basics.apis.cache.server;

import java.util.function.Function;

/**
 * @author Amir
 */
public interface IVaselineCacheConfigurer<C> {

    Function<String, C> getCache();
}
