package ir.amv.os.vaseline.basics.karaf.feature.cache;

import com.hazelcast.cache.ICache;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.basics.cache.api.server.IVaselineCacheApi;
import ir.amv.os.vaseline.basics.cache.api.server.IVaselineCacheConfigurer;
import ir.amv.os.vaseline.basics.cache.hazelcast.osgi.ModuleHazelcastCacheBasket;
import ir.amv.os.vaseline.basics.cache.hazelcast.osgi.VaselineCacheConfigurerImpl;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class CacheStepDefs {

    @Inject
    IVaselineCacheConfigurer<ICache> cacheConfigurer;

    @Inject
    IVaselineCacheApi cacheApi;

    @Inject
    private ConfigurationAdmin confAdmin;

    @And("I have cache {string}")
    public void iHaveCache(String cacheName) throws IOException, InterruptedException {
        Configuration configuration = confAdmin.createFactoryConfiguration(VaselineCacheConfigurerImpl
                .VASELINE_CACHE_HAZELCAST_PID, null);
        Dictionary<String, Object> config = new Hashtable<>();
        config.put(ModuleHazelcastCacheBasket.MODULE_NAME, "testModule");
        config.put(ModuleHazelcastCacheBasket.CACHE_NAME, cacheName);
        config.put(ModuleHazelcastCacheBasket.CACHE_NAME + ModuleHazelcastCacheBasket.CACHE_CONF_SEPARATOR +
                ModuleHazelcastCacheBasket.CACHE_BACKUP_COUNT_POSTFIX, 1);
        configuration.update(config);
        Thread.sleep(1000); // update happens async
    }

    @And("I put value {string} with key {string} to cache {string}")
    public void iPutValueToCache(String value, String cacheKey, String cacheName) {
        Function<String, ICache> cacheFunction = cacheConfigurer.getCache();
        ICache cache = cacheFunction.apply("testModule" + VaselineCacheConfigurerImpl.CACHE_QN_QUALIFIER + cacheName);
        cache.put(cacheKey, value);
    }

    @Then("I get value {string} with key {string} from cache {string}")
    public void iGetValueWithKeyFromCache(String value, String cacheKey, String cacheName) {
        Function<String, ICache> cacheFunction = cacheConfigurer.getCache();
        ICache cache = cacheFunction.apply("testModule" + VaselineCacheConfigurerImpl.CACHE_QN_QUALIFIER + cacheName);
        Object cachedValue = cache.get(cacheKey);
        assertEquals(value, cachedValue);
    }
}
