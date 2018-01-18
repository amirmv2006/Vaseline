package ir.amv.os.vaseline.ws.osgi.rest.jersey.publisher;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
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
public class JerseyRestConfig {

    @Reference
    public void setConfAdmin(ConfigurationAdmin confAdmin) throws IOException {
        Configuration configuration = confAdmin.getConfiguration("com.eclipsesource.jaxrs.connector", null);
        Dictionary<String, Object> root = configuration.getProperties() == null ? new Hashtable<>() : configuration.getProperties();
        root.put("Root", "/rest");
        configuration.update(root);
    }

}
