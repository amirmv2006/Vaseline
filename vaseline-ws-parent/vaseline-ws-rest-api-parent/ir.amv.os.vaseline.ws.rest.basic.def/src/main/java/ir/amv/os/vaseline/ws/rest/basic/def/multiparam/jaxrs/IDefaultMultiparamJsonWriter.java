package ir.amv.os.vaseline.ws.rest.basic.def.multiparam.jaxrs;

import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.ws.rest.basic.api.base.generic.GsonIgnoreGenericType;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Amir
 */
public interface IDefaultMultiparamJsonWriter extends MessageBodyWriter<Object> {

    IVaselineJsonConverter getVaselineJsonConverter();

    @Override
    default boolean isWriteable(final Class<?> aClass, final Type type, final Annotation[] annotations, final MediaType
            mediaType) {
        return true;
    }

    @Override
    default long getSize(final Object o, final Class<?> aClass, final Type type, final Annotation[] annotations, final
    MediaType mediaType) {
        return -1;
    }

    @Override
    default void writeTo(
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
            getVaselineJsonConverter().toJson(object, jsonType, writer);
        } finally {
            writer.close();
        }
    }
}
