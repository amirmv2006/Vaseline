package ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.fqnconverter;

/**
 * Created by amv on 12/17/16.
 */
public interface IBaseFqnConverter {

    String convertFqn(String fqn);
    String convertPackage(String pkg);
    String convertClassName(String className, boolean shouldChangeClassName);
}
