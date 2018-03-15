package ir.amv.os.vaseline.basics.osgi.base.conf;

import java.util.Dictionary;

/**
 * @author Amir
 */
public interface IBaseConfig {

    default <T> T getConfig(final String propertyName, final Dictionary<?, ?> properties, final T defaultValue) {
        String prefix = "${env";
        Object value;
        if (propertyName.startsWith(prefix)) {
            String environmentVariableName = propertyName.substring(prefix.length() + 1 /*. or :*/,
                    propertyName.length() - 1);
            value = System.getenv(environmentVariableName);
        } else {
            value = properties.get(propertyName);
        }
        return value == null ? defaultValue : (T) value;
    }
}
