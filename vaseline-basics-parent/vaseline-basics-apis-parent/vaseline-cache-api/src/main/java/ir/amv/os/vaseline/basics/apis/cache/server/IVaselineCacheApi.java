package ir.amv.os.vaseline.basics.apis.cache.server;

import javax.cache.expiry.ExpiryPolicy;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Amir
 */
public interface IVaselineCacheApi {

    <K, V> V cacheGet(final String cacheName, final K entryKey);

    <K, V> void cachePut(final String cacheName, final K entryKey, final V value, final ExpiryPolicy expiryPolicy);

    <K, V> V cacheResult(String cacheName, K entryKey, Function<Consumer<ExpiryPolicy>, V> supplier);
}
