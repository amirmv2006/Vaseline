package ir.amv.os.vaseline.basics.spring.i18n.server.base.impl;

import ir.amv.os.vaseline.basics.apis.i18n.server.file.resolver.IVaselineI18nFileProvider;

/**
 * Created by AMV on 2/10/2016.
 */
public class VaselineClasspathI18nFileProviderImpl implements IVaselineI18nFileProvider {

    private String filePath;

    public VaselineClasspathI18nFileProviderImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String fileBaseName() {
        return "classpath:" + filePath;
    }
}
