package ir.amv.os.vaseline.ws.rest.basic.def.multiparam.jaxrs;

import ir.amv.os.vaseline.basics.core.api.utils.io.IOUtils;
import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.ws.rest.basic.api.base.multiparam.annot.JsonMultParam;
import ir.amv.os.vaseline.ws.rest.basic.api.base.multiparam.annot.JsonParam;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amir
 */
public interface IDefaultMultiparamJsonReader extends MessageBodyReader<Object> {

    IVaselineJsonConverter getVaselineJsonConverter();

    @Override
    default boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    default Object readFrom(
            final Class<Object> aClass,
            final Type genericType,
            final Annotation[] annotations,
            final MediaType mediaType,
            final MultivaluedMap<String, String> multivaluedMap,
            final InputStream entityStream) throws IOException, WebApplicationException {
        String json = IOUtils.inputStreamToString(entityStream);
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
                String subTreeJson = getVaselineJsonConverter().getSubTree(json, paramName);
                Object paramVal = getVaselineJsonConverter().fromJson(subTreeJson, param.paramType());
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
        return getVaselineJsonConverter().fromJson(json, jsonType);
    }

}
