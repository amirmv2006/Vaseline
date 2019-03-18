package ir.amv.os.vaseline.ws.rest.gson.ext.osgi;

import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.ws.rest.basic.def.multiparam.jaxrs.IBaseImplementedMultiparamJsonReader;
import ir.amv.os.vaseline.ws.rest.basic.def.multiparam.jaxrs.IBaseImplementedMultiparamJsonWriter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                MessageBodyWriter.class,
                MessageBodyReader.class
        }
)
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VaselineOSGiMultiparamHandler implements MessageBodyWriter<Object>,
        MessageBodyReader<Object>, IBaseImplementedMultiparamJsonReader, IBaseImplementedMultiparamJsonWriter {

    private IVaselineJsonConverter vaselineJsonConverter;

    @Override
    public IVaselineJsonConverter getVaselineJsonConverter() {
        return vaselineJsonConverter;
    }

    @Reference(
            cardinality = ReferenceCardinality.OPTIONAL,
            policyOption = ReferencePolicyOption.GREEDY
    )
    public void setVaselineJsonConverter(final IVaselineJsonConverter vaselineJsonConverter) {
        this.vaselineJsonConverter = vaselineJsonConverter;
    }
}
