package ir.amv.os.vaseline.basics.spring.i18n.server.base.impl;

import ir.amv.os.vaseline.basics.i18n.api.server.file.resolver.IVaselineI18nFileProvider;

/**
 * Created by AMV on 2/10/2016.
 */
public class VaselineClasspathI18nFileProviderImpl implements IVaselineI18nFileProvider {

    private String filePath;
    private ClassLoader classLoader;

    public VaselineClasspathI18nFileProviderImpl(String filePath) {
        this.filePath = filePath;
        this.classLoader = getClass().getClassLoader();
    }

    public VaselineClasspathI18nFileProviderImpl(final String filePath, final ClassLoader classLoader) {
        this.filePath = filePath;
        this.classLoader = classLoader;
    }

    @Override
    public String fileBaseName() {
        return "classpath:" + filePath;
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
