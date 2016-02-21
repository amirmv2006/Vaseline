/*
 * Copyright 2005-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ir.amv.os.vaseline.base.mapper.config;

import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionUtil;
import org.dozer.*;
import org.dozer.cache.CacheManager;
import org.dozer.cache.DozerCacheManager;
import org.dozer.cache.DozerCacheType;
import org.dozer.classmap.ClassMappings;
import org.dozer.classmap.Configuration;
import org.dozer.classmap.MappingFileData;
import org.dozer.config.GlobalSettings;
import org.dozer.converters.CustomConverterDescription;
import org.dozer.event.DozerEventManager;
import org.dozer.factory.DestBeanCreator;
import org.dozer.loader.CustomMappingsLoader;
import org.dozer.loader.LoadMappingsResult;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.stats.GlobalStatistics;
import org.dozer.stats.StatisticType;
import org.dozer.stats.StatisticsInterceptor;
import org.dozer.stats.StatisticsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Public Dozer Mapper implementation. This should be used/defined as a singleton within your application. This class
 * perfoms several one time initializations and loads the custom xml mappings, so you will not want to create many
 * instances of it for performance reasons. Typically a system will only have one DozerBeanMapper instance per VM. If
 * you are using an IOC framework (i.e Spring), define the Mapper as singleton="true". If you are not using an IOC
 * framework, a DozerBeanMapperSingletonWrapper convenience class has been provided in the Dozer jar.
 * <p/>
 * It is technically possible to have multiple DozerBeanMapper instances initialized, but it will hinder internal
 * performance optimizations such as caching.
 *
 * @author tierney.matt
 * @author garsombke.franz
 * @author dmitry.buzdin
 */
public class VaselineDozerMapper implements Mapper {

    private static final Logger log = LoggerFactory.getLogger(VaselineDozerMapper.class);
    private static final StatisticsManager statsMgr = GlobalStatistics.getInstance().getStatsMgr();

    private final AtomicBoolean initializing = new AtomicBoolean(false);
    private final CountDownLatch ready = new CountDownLatch(1);

    /*
     * Accessible for custom injection
     */
    private final List<String> mappingFiles = new ArrayList<String>();
    private final List<CustomConverter> customConverters = new ArrayList<CustomConverter>();
    private final List<MappingFileData> builderMappings = new ArrayList<MappingFileData>();
    private final Map<String, CustomConverter> customConvertersWithId = new HashMap<String, CustomConverter>();
    private List<? extends DozerEventListener> eventListeners = new ArrayList<DozerEventListener>();

    private CustomFieldMapper customFieldMapper;

    /*
     * Not accessible for injection
     */
    private ClassMappings customMappings;
    private Configuration globalConfiguration;
    // There are no global caches. Caches are per bean mapper instance
    private final CacheManager cacheManager = new DozerCacheManager();
    private DozerEventManager eventManager;
    private List<Class<?>> customConvertersClasses;

    public VaselineDozerMapper() {
        this(Collections.<String>emptyList());
    }

    public VaselineDozerMapper(List<String> mappingFiles) {
        this.mappingFiles.addAll(mappingFiles);
        init();
    }

    /**
     * {@inheritDoc}
     */
    public void map(Object source, Object destination, String mapId) throws MappingException {
        getMappingProcessor().map(source, destination, mapId);
    }

    /**
     * {@inheritDoc}
     */
    public <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException {
        return getMappingProcessor().map(source, destinationClass, mapId);
    }

    /**
     * {@inheritDoc}
     */
    public <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        return getMappingProcessor().map(source, destinationClass);
    }

    /**
     * {@inheritDoc}
     */
    public void map(Object source, Object destination) throws MappingException {
        getMappingProcessor().map(source, destination);
    }

    /**
     * Returns list of provided mapping file URLs
     *
     * @return unmodifiable list of mapping files
     */
    public List<String> getMappingFiles() {
        return Collections.unmodifiableList(mappingFiles);
    }

    /**
     * Sets list of URLs for custom XML mapping files, which are loaded when mapper gets initialized.
     * It is possible to load files from file system via file: prefix. If no prefix is given mapping files are
     * loaded from classpath and can be packaged along with the application.
     *
     * @param mappingFileUrls URLs referencing custom mapping files
     * @see java.net.URL
     */
    public void setMappingFiles(List<String> mappingFileUrls) {
        checkIfInitialized();
        this.mappingFiles.clear();
        this.mappingFiles.addAll(mappingFileUrls);
    }

    public void setFactories(Map<String, BeanFactory> factories) {
        checkIfInitialized();
        DestBeanCreator.setStoredFactories(factories);
    }

    public void setCustomConverters(List<CustomConverter> customConverters) {
        checkIfInitialized();
        this.customConverters.clear();
        this.customConverters.addAll(customConverters);
    }

    public List<CustomConverter> getCustomConverters() {
        return Collections.unmodifiableList(customConverters);
    }

    public Map<String, CustomConverter> getCustomConvertersWithId() {
        return Collections.unmodifiableMap(customConvertersWithId);
    }

    private void init() {
        DozerInitializer.getInstance().init();

        log.info("Initializing a new instance of dozer bean mapper.");

        // initialize any bean mapper caches. These caches are only visible to the bean mapper instance and
        // are not shared across the VM.
        GlobalSettings globalSettings = GlobalSettings.getInstance();
        cacheManager.addCache(DozerCacheType.CONVERTER_BY_DEST_TYPE.name(), globalSettings.getConverterByDestTypeCacheMaxSize());
        cacheManager.addCache(DozerCacheType.SUPER_TYPE_CHECK.name(), globalSettings.getSuperTypesCacheMaxSize());

        // stats
        statsMgr.increment(StatisticType.MAPPER_INSTANCES_COUNT);
    }

    public void destroy() {
        DozerInitializer.getInstance().destroy();
    }

    protected Mapper getMappingProcessor() {

        if (initializing.compareAndSet(false, true)) {
            try {
                loadCustomMappings();
                eventManager = new DozerEventManager(eventListeners);
            } finally {
                ready.countDown();
            }
        }

        try {
            ready.await();
        } catch (InterruptedException e) {
            log.error("Thread interrupted: ", e);
        }

        Mapper processor = new MappingProcessor(customMappings, globalConfiguration, cacheManager, statsMgr, customConverters,
                eventManager, getCustomFieldMapper(), customConvertersWithId){};

        // If statistics are enabled, then Proxy the processor with a statistics interceptor
        if (statsMgr.isStatisticsEnabled()) {
            processor = (Mapper) Proxy.newProxyInstance(processor.getClass().getClassLoader(), processor.getClass().getInterfaces(),
                    new StatisticsInterceptor(processor, statsMgr));
        }

        return processor;
    }

    void loadCustomMappings() {
        CustomMappingsLoader customMappingsLoader = new CustomMappingsLoader();
        LoadMappingsResult loadMappingsResult = customMappingsLoader.load(mappingFiles, builderMappings);
        this.customMappings = loadMappingsResult.getCustomMappings();
        // add custom converters here
        for (Class<?> customConvertersClass : customConvertersClasses) {
            Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(customConvertersClass);
            CustomConverterDescription customConverterDesc = new CustomConverterDescription();
            customConverterDesc.setClassA(genericArgumentClasses[0]);
            customConverterDesc.setClassB(genericArgumentClasses[1]);
            customConverterDesc.setType(customConvertersClass);
            loadMappingsResult.getGlobalConfiguration().getCustomConverters().addConverter(customConverterDesc);
        }
        this.globalConfiguration = loadMappingsResult.getGlobalConfiguration();
    }

    public void setMappings(List<? extends BeanMappingBuilder> mappingBuilder) {
        for (BeanMappingBuilder builder : mappingBuilder) {
            addMapping(builder);
        }
    }

    public void addMapping(BeanMappingBuilder mappingBuilder) {
        checkIfInitialized();
        MappingFileData mappingFileData = mappingBuilder.build();
        builderMappings.add(mappingFileData);
    }

    public List<? extends DozerEventListener> getEventListeners() {
        return Collections.unmodifiableList(eventListeners);
    }

    public void setEventListeners(List<? extends DozerEventListener> eventListeners) {
        checkIfInitialized();
        this.eventListeners = eventListeners;
    }

    public CustomFieldMapper getCustomFieldMapper() {
        return customFieldMapper;
    }

    public void setCustomFieldMapper(CustomFieldMapper customFieldMapper) {
        checkIfInitialized();
        this.customFieldMapper = customFieldMapper;
    }

    /**
     * Converters passed with this method could be further referenced in mappings via its unique id.
     * Converter instances passed that way are considered stateful and will not be initialized for each mapping.
     *
     * @param customConvertersWithId converter id to converter instance map
     */
    public void setCustomConvertersWithId(Map<String, CustomConverter> customConvertersWithId) {
        checkIfInitialized();
        this.customConvertersWithId.clear();
        this.customConvertersWithId.putAll(customConvertersWithId);
    }

    private void checkIfInitialized() {
        if (ready.getCount() == 0) {
            throw new MappingException("Dozer Bean Mapper is already initialized! Modify settings before calling map()");
        }
    }

    public void setCustomConvertersClasses(List<Class<?>> customConvertersClasses) {
        this.customConvertersClasses = customConvertersClasses;
    }
}
