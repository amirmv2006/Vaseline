package ir.amv.os.vaseline.basics.cache.hazelcast.osgi;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.osgi.HazelcastOSGiService;
import org.osgi.service.cm.ConfigurationException;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Amir
 */
public class ModuleHazelcastCacheBasket {

    public static final String MODULE_NAME = "moduleName";
    public static final String INSTANCE_NAME = "instanceName";
    public static final String CACHE_NAME = "cacheName";
    public static final String CACHE_JAVA_CONF_POSTFIX = "cacheName";
    public static final String CACHE_BACKUP_COUNT_POSTFIX = "backupCount";
    public static final String CACHE_CONF_SEPARATOR = ".";

    private Supplier<HazelcastOSGiService> hazelcastServiceSupplier;
    private HazelcastInstance hazelcastInstance;
    private String moduleName;
    private String instanceName;
    private Map<String, CacheSimpleConfig> cacheConfigMap;

    public ModuleHazelcastCacheBasket(final Supplier<HazelcastOSGiService> hazelcastServiceSupplier, final
    Dictionary<String, ?> properties) throws ConfigurationException {
        this.hazelcastServiceSupplier = hazelcastServiceSupplier;
        moduleName = getValue(properties, MODULE_NAME);
        instanceName = getValueOrDefault(properties, INSTANCE_NAME, moduleName);
        cacheConfigMap = new HashMap<>();
        String cacheNameKey = CACHE_NAME;
        int i = 0;
        String cacheName = getValueOrDefault(properties, cacheNameKey, null);
        while (cacheName != null) {
            CacheSimpleConfig cacheSimpleConfig = getValueOrDefault(properties, cacheNameKey + CACHE_CONF_SEPARATOR +
                    CACHE_JAVA_CONF_POSTFIX, new CacheSimpleConfig());
            // read the rest of configs here
            Integer backupCount = getValueOrDefault(properties, cacheNameKey + CACHE_CONF_SEPARATOR +
                    CACHE_BACKUP_COUNT_POSTFIX, null);
            if (backupCount != null) {
                cacheSimpleConfig.setBackupCount(backupCount);
            }
            cacheConfigMap.put(cacheName, cacheSimpleConfig);
            i++;
            cacheNameKey += i;
            cacheName = getValueOrDefault(properties, cacheNameKey, null);
        }
    }

    private <T> T getValueOrDefault(final Dictionary<String, ?> properties, final String propertyName, final T
            defaultValue) {
        T propVal = (T) properties.get(propertyName);
        return propVal == null ? defaultValue : propVal;
    }

    private String getValue(final Dictionary<String, ?> properties, final String propertyName) throws ConfigurationException {
        Object propVal = properties.get(propertyName);
        if (propVal == null) {
            throw new ConfigurationException(propertyName, String.format("property %s can not be null", propertyName));
        }
        return (String) propVal;
    }

    public String getModuleName() {
        return moduleName;
    }

    public HazelcastInstance getHazelcastInstance() {
        if (hazelcastInstance == null) {
            HazelcastOSGiService hazelcastOSGiService = hazelcastServiceSupplier.get();
            Config config = new Config(instanceName);
            config.setClassLoader(ModuleHazelcastCacheBasket.class.getClassLoader());
            config.setCacheConfigs(cacheConfigMap);
            hazelcastInstance = hazelcastOSGiService.newHazelcastInstance(config);
        }
        return hazelcastInstance;
    }

    public boolean containsCacheQN(final String cacheQN) {
        for (String cacheName : cacheConfigMap.keySet()) {
            String myCacheQN = instanceName + VaselineCacheConfigurerImpl.CACHE_QN_QUALIFIER + cacheName;
            if (myCacheQN.equals(cacheQN)) {
                return true;
            }
        }
        return false;
    }
}
