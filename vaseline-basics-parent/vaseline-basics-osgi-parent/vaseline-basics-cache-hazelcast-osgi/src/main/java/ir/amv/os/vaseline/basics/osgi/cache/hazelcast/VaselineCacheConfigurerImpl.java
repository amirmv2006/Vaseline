package ir.amv.os.vaseline.basics.osgi.cache.hazelcast;

import com.hazelcast.cache.ICache;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICacheManager;
import com.hazelcast.osgi.HazelcastOSGiService;
import ir.amv.os.vaseline.basics.apis.cache.server.IVaselineCacheConfigurer;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineCacheConfigurer.class,
                ManagedServiceFactory.class
        },
        property = Constants.SERVICE_PID + "=" + VaselineCacheConfigurerImpl.VASELINE_CACHE_HAZELCAST_PID
)
public class VaselineCacheConfigurerImpl
        implements IVaselineCacheConfigurer<ICache>, ManagedServiceFactory {
    public static final String CACHE_QN_QUALIFIER = ":";
    public static final String VASELINE_CACHE_HAZELCAST_PID = "ir.amv.os.vaseline.cache.hazelcast";

    private HazelcastOSGiService hazelcastOSGiService;
    private Map<String, ModuleHazelcastCacheBasket> hazelcastInstanceMap = new HashMap<>();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void updated(final String pid, final Dictionary<String, ?> properties) throws ConfigurationException {
        // create an instance for pid
        ModuleHazelcastCacheBasket moduleHazelcastCacheBasket = new ModuleHazelcastCacheBasket(() ->
                hazelcastOSGiService, properties);
        hazelcastInstanceMap.put(pid, moduleHazelcastCacheBasket);
    }

    @Override
    public void deleted(final String pid) {
        // remove instance
        hazelcastInstanceMap.remove(pid);
    }

    @Override
    public Function<String, ICache> getCache() {
        return cacheQN -> {
            Set<Map.Entry<String, ModuleHazelcastCacheBasket>> entries = hazelcastInstanceMap.entrySet();
            for (Map.Entry<String, ModuleHazelcastCacheBasket> entry : entries) {
                if (entry.getValue().containsCacheQN(cacheQN)) {
                    HazelcastInstance hazelcastInstance = entry.getValue().getHazelcastInstance();
                    ICacheManager cacheManager = hazelcastInstance.getCacheManager();
                    return cacheManager.getCache(getCacheName(cacheQN));
                }
            }
            return null;
        };
    }

    private String getCacheName(final String cacheQN) {
        return cacheQN.split(CACHE_QN_QUALIFIER)[1];
    }

    @Reference
    public void setHazelcastOSGiService(final HazelcastOSGiService hazelcastOSGiService) {
        this.hazelcastOSGiService = hazelcastOSGiService;
    }
}
