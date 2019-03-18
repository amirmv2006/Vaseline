package ir.amv.os.vaseline.ws.spring.rest.jersey.multiparam.msgrw;

import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.ws.rest.basic.def.multiparam.jaxrs.IBaseImplementedMultiparamJsonReader;
import ir.amv.os.vaseline.ws.rest.basic.def.multiparam.jaxrs.IBaseImplementedMultiparamJsonWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class VaselineSpringMultiparamHandler implements MessageBodyWriter<Object>,
		MessageBodyReader<Object>, IBaseImplementedMultiparamJsonReader, IBaseImplementedMultiparamJsonWriter {

	private final IVaselineJsonConverter vaselineJsonConverter;

	@Autowired
    public VaselineSpringMultiparamHandler(IVaselineJsonConverter vaselineJsonConverter) {
        this.vaselineJsonConverter = vaselineJsonConverter;
    }

    @Override
	public IVaselineJsonConverter getVaselineJsonConverter() {
		return vaselineJsonConverter;
	}
}