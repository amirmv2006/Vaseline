package ir.amv.os.vaseline.basics.apis.cache.server;

import javax.cache.expiry.ExpiryPolicy;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Amir
 */
public interface IVaselineCacheApi {

    <K, V> void cachePut(final String cacheName, final K entryKey, final V value, final ExpiryPolicy expiryPolicy);

    <K, V> V cacheResult(String cacheName, K entryKey, Function<Consumer<ExpiryPolicy>, V> supplier);
}
