package ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.fqnconverter.impl;

import ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.fqnconverter.IBaseFqnConverter;

/**
 * Created by amv on 12/17/16.
 */
public class BaseFqnConverterImpl
        implements IBaseFqnConverter {

    private String searchObjectClassNamePrefix = "I";
    private String searchObjectClassNamePostFix = "SearchObject";
    private String entityClassNamePostFix = "Entity";
    private String entBasePackage;
    private String isoBasePackage;

    public BaseFqnConverterImpl(String searchObjectClassNamePrefix, String searchObjectClassNamePostFix, String entityClassNamePostFix, String entBasePackage, String isoBasePackage) {
        this.searchObjectClassNamePrefix = searchObjectClassNamePrefix;
        this.searchObjectClassNamePostFix = searchObjectClassNamePostFix;
        this.entityClassNamePostFix = entityClassNamePostFix;
        this.entBasePackage = entBasePackage;
        this.isoBasePackage = isoBasePackage;
    }

    protected String convertPackage(String pkg, String srcBasePkg, String dstBasePkg) {
        if (!pkg.startsWith(srcBasePkg)) {
            throw new IllegalArgumentException(pkg + " doesn't start with " + srcBasePkg);
        }
        return dstBasePkg + pkg.substring(srcBasePkg.length());
    }

    @Override
    public String convertFqn(String fqn) {
        String convertedPackage = "";
        String className;
        if (fqn.contains(".")) {
            convertedPackage = convertPackage(fqn.substring(0, fqn.lastIndexOf('.')));
            className = fqn.substring(fqn.lastIndexOf('.') + 1);
        } else {
            className = fqn;
        }
        if (fqn.startsWith(entBasePackage)) {
            return convertedPackage + "." + convertClassName(className, true);
        }
        return convertedPackage + "." + className;
    }

    @Override
    public String convertPackage(String pkg) {
        if (pkg.startsWith(entBasePackage)) {
            return convertPackage(pkg, entBasePackage, isoBasePackage);
        }
        return pkg;
    }

    @Override
    public String convertClassName(String className, boolean shouldChangeClassName) {
        if (shouldChangeClassName) {
            className = className.endsWith(entityClassNamePostFix) ? className.substring(0, className.length() - entityClassNamePostFix.length()) : className;
            return searchObjectClassNamePrefix + className + searchObjectClassNamePostFix;
        }
        return className;
    }
}
