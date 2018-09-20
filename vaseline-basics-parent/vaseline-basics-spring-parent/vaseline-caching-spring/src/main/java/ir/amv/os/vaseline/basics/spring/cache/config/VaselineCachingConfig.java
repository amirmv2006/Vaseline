package ir.amv.os.vaseline.basics.spring.cache.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * TODO is this really necessary? isn't spring doing this part automatically?
 * Created by AMV on 2/11/2016.
 */
@Configuration
@EnableCaching
public class VaselineCachingConfig implements CachingConfigurer, ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(net.sf.ehcache.CacheManager.class)
    public net.sf.ehcache.CacheManager ehCacheManager() {
        Map<String, CacheConfiguration> cacheConfigurationMap = applicationContext.getBeansOfType(CacheConfiguration.class);
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        for (CacheConfiguration cacheConfiguration : cacheConfigurationMap.values()) {
            config.addCache(cacheConfiguration);
        }
        return net.sf.ehcache.CacheManager.create(config);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    @Bean
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}