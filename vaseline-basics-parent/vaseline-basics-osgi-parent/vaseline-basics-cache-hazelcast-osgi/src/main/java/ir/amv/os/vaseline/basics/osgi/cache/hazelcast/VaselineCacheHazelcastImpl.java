package ir.amv.os.vaseline.basics.osgi.cache.hazelcast;

import com.hazelcast.cache.ICache;
import ir.amv.os.vaseline.basics.apis.cache.server.IVaselineCacheApi;
import ir.amv.os.vaseline.basics.apis.cache.server.IVaselineCacheConfigurer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.cache.expiry.ExpiryPolicy;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineCacheApi.class
)
public class VaselineCacheHazelcastImpl implements IVaselineCacheApi {

    private IVaselineCacheConfigurer<ICache> vaselineCacheConfigurer;

    @Override
    public <K, V> V cacheGet(final String cacheName, final K entryKey) {
        ICache<Object, Object> cache = getCache(cacheName);
        return (V) cache.get(entryKey);
    }

    @Override
    public <K, V> void cachePut(final String cacheName, final K entryKey, final V value, final ExpiryPolicy
            expiryPolicy) {
        ICache<K, V> cache = getCache(cacheName);
        cache.put(entryKey, value, expiryPolicy);
    }

    private <K, V> ICache<K, V> getCache(final String cacheName) {
        Function<String, ICache> getCacheFunction = vaselineCacheConfigurer.getCache();
        return (ICache<K, V>) getCacheFunction.apply(cacheName);
    }

    @Override
    public <K, V> V cacheResult(final String cacheName, final K entryKey, final Function<Consumer<ExpiryPolicy>, V> supplier) {
        ICache<K, V> cache = getCache(cacheName);
        if (cache.containsKey(entryKey)) {
            return cache.get(entryKey);
        }
        AtomicReference<ExpiryPolicy> reference = new AtomicReference<>();
        V result = supplier.apply(reference::set);
        cache.put(entryKey, result, reference.get());
        return result;
    }

    @Reference
    public void setVaselineCacheConfigurer(final IVaselineCacheConfigurer<ICache> vaselineCacheConfigurer) {
        this.vaselineCacheConfigurer = vaselineCacheConfigurer;
    }
}
