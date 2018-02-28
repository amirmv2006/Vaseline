package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.cache.hazelcast;

import com.hazelcast.cache.ICache;
import com.hazelcast.config.CacheSimpleConfig;
import ir.amv.os.vaseline.basics.apis.cache.server.IVaselineCacheConfigurer;
import ir.amv.os.vaseline.basics.osgi.cache.hazelcast.ModuleHazelcastCacheBasket;
import ir.amv.os.vaseline.basics.osgi.cache.hazelcast.VaselineCacheConfigurerImpl;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.config.IVaselineOAuthConfig;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Amir
 */
@Component(
        immediate = true
)
public class VaselineOAuth2CacheHazelcastConfig {

//    public static final String AUTHENTICATION_CACHE_CONFIG_PID = VaselineCacheConfigurerImpl
//            .VASELINE_CACHE_HAZELCAST_PID + ".oauth";

    private ConfigurationAdmin confAdmin;

    @Activate
    public void initialize() throws IOException {
        Configuration configuration = confAdmin.createFactoryConfiguration(VaselineCacheConfigurerImpl
                .VASELINE_CACHE_HAZELCAST_PID, null);
        Dictionary<String, Object> config = new Hashtable<>();
        config.put(ModuleHazelcastCacheBasket.MODULE_NAME, IVaselineOAuthConfig.AUTHENTICATION_CACHE_GROUP);
        config.put(ModuleHazelcastCacheBasket.CACHE_NAME, IVaselineOAuthConfig.TOKEN_CACHE_NAME);
//        CacheSimpleConfig tokenCacheConf = new CacheSimpleConfig();
//        tokenCacheConf.setBackupCount(1);
//        config.put(IVaselineOAuthConfig.TOKEN_CACHE_NAME + ModuleHazelcastCacheBasket.CACHE_CONF_SEPARATOR +
//                ModuleHazelcastCacheBasket.CACHE_JAVA_CONF_POSTFIX, IVaselineOAuthConfig.TOKEN_CACHE_NAME);
        config.put(ModuleHazelcastCacheBasket.CACHE_NAME + ModuleHazelcastCacheBasket.CACHE_CONF_SEPARATOR +
                ModuleHazelcastCacheBasket.CACHE_BACKUP_COUNT_POSTFIX, 1);
        configuration.update(config);
    }

    @Reference
    public void setConfAdmin(final ConfigurationAdmin confAdmin) {
        this.confAdmin = confAdmin;
    }
}
