package com.github.amirmv2006.base.fqnconverter;

/**
 * Created by amv on 12/17/16.
 */
public interface IBaseFqnConverter {

    String convertFqn(String fqn);
    String convertPackage(String pkg);
    String convertClassName(String className, boolean shouldChangeClassName);
}
