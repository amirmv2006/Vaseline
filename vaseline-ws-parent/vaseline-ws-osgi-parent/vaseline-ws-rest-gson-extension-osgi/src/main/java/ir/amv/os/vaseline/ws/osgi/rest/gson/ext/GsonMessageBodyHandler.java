package ir.amv.os.vaseline.ws.osgi.rest.gson.ext;

import ir.amv.os.vaseline.basics.apis.json.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.ws.rest.apis.basic.layer.base.generic.GsonIgnoreGenericType;
import ir.amv.os.vaseline.ws.rest.apis.basic.layer.base.multiparam.annot.JsonMultParam;
import ir.amv.os.vaseline.ws.rest.apis.basic.layer.base.multiparam.annot.JsonParam;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class GsonMessageBodyHandler implements MessageBodyWriter<Object>,
        MessageBodyReader<Object> {

    private static final String UTF_8 = "UTF-8";
    private IVaselineJsonConverter vaselineJsonConverter;

    @Override
    public boolean isReadable(final Class<?> aClass, final Type type, final Annotation[] annotations, final MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(
            final Class<Object> aClass,
            final Type genericType,
            final Annotation[] annotations,
            final MediaType mediaType,
            final MultivaluedMap<String, String> multivaluedMap,
            final InputStream entityStream) throws IOException, WebApplicationException {
        String json = inputStreamToString(entityStream);
        if (annotations.length > 0) {
            JsonMultParam jsonParam = null;
            for (Annotation annotation : annotations) {
                if (annotation instanceof JsonMultParam) {
                    jsonParam = (JsonMultParam) annotation;
                }
            }
            List<JsonParam> params = Arrays.asList(jsonParam.value());
            Map<String, Object> map = new HashMap<String, Object>(params.size());
            for (JsonParam param : params) {
                String paramName = param.paramName();
                String subTreeJson = vaselineJsonConverter.getSubTree(json, paramName);
                Object paramVal = vaselineJsonConverter.fromJson(subTreeJson, param.paramType());
                map.put(paramName, paramVal);
            }
            return map;
        }
        Type jsonType;
        if (aClass.equals(genericType)) {
            jsonType = aClass;
        } else {
            jsonType = genericType;
        }
        return vaselineJsonConverter.fromJson(json, jsonType);
    }

    private String inputStreamToString(final InputStream entityStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        int c = 0;
        try (Reader streamReader = new InputStreamReader(entityStream)) {
            while ((c = streamReader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

    @Override
    public boolean isWriteable(final Class<?> aClass, final Type type, final Annotation[] annotations, final MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(final Object o, final Class<?> aClass, final Type type, final Annotation[] annotations, final MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(
            final Object object,
            final Class<?> type,
            final Type genericType,
            final Annotation[] annotations,
            final MediaType mediaType,
            final MultivaluedMap<String, Object> multivaluedMap,
            final OutputStream entityStream) throws IOException, WebApplicationException {
        OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8);
        try {
            Type jsonType;
            if (type.isAnnotationPresent(GsonIgnoreGenericType.class) || type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            vaselineJsonConverter.toJson(object, jsonType, writer);
        } finally {
            writer.close();
        }
    }

    @Reference
    public void setVaselineJsonConverter(final IVaselineJsonConverter vaselineJsonConverter) {
        this.vaselineJsonConverter = vaselineJsonConverter;
    }
}
