package ir.amv.os.vaseline.ws.rest.jersey.publisher.osgi;

import com.eclipsesource.jaxrs.consumer.ConsumerPublisher;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Amir
 */
@Component(
        immediate = true
)
public class JerseyRestConfig {

//    private ConsumerPublisher consumerPublisher;
//    private List<MessageBodyWriter> bodyWriters = new ArrayList<>();
//    private List<MessageBodyReader> bodyReaders = new ArrayList<>();


    @Reference
    public void setConfAdmin(ConfigurationAdmin confAdmin) throws IOException {
        Configuration configuration = confAdmin.getConfiguration("com.eclipsesource.jaxrs.connector", null);
        Dictionary<String, Object> root = configuration.getProperties() == null ? new Hashtable<>() : configuration.getProperties();
        root.put("Root", "/rest");
        configuration.update(root);
    }

//    public void publish() {
//        consumerPublisher.publishConsumers();
//    }
//
//    @Reference(
//            cardinality = ReferenceCardinality.AT_LEAST_ONE
//    )
//    public void addMessageBodyWriter(MessageBodyWriter messageBodyWriter) {
//
//    }

}
