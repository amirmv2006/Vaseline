package ir.amv.os.vaseline.ws.rest.config.gsonhandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.amv.os.vaseline.ws.rest.config.gsonhandler.annot.GsonIgnoreGenericType;
import ir.amv.os.vaseline.ws.rest.config.gsonhandler.classgenerator.JavassistClassGenerator;
import ir.amv.os.vaseline.ws.rest.server.multiparam.annot.JsonMultParam;
import ir.amv.os.vaseline.ws.rest.server.multiparam.annot.JsonParam;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class GsonMessageBodyHandler implements MessageBodyWriter<Object>,
		MessageBodyReader<Object>, ApplicationContextAware {

	private static final String UTF_8 = "UTF-8";
	private ApplicationContext applicationContext;
	private Gson gson;

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
		InputStreamReader streamReader = null;
		try {
			streamReader = new InputStreamReader(entityStream, UTF_8);
            if (annotations.length > 0) {
                JsonMultParam jsonParam = null;
                for (Annotation annotation : annotations) {
                    if (annotation instanceof JsonMultParam) {
                        jsonParam = (JsonMultParam) annotation;
                    }
                }
                List<JsonParam> params = Arrays.asList(jsonParam.value());
                Class<?> classForGson = JavassistClassGenerator.getClassForMultiParam(params);
                Object o = gson().fromJson(streamReader, classForGson);
                Map<String, Object> map = new HashMap<String, Object>(params.size());
                for (JsonParam param : params) {
                    String paramName = param.paramName();
                    Field field = classForGson.getField(paramName);
                    field.setAccessible(true);
                    Object paramVal = field.get(o);
                    map.put(paramName, paramVal);
                }
                return map;
            }
            Type jsonType;
			if (type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}
			return gson().fromJson(streamReader, jsonType);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				if (streamReader != null) {
					streamReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean isAnnotPresent(Annotation[] annotations, Class<?> annotClass) {
		for (Annotation annotation : annotations) {
			if (annotation.getClass().equals(annotClass)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Object object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Object object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8);
		try {
			Type jsonType;
			if (type.isAnnotationPresent(GsonIgnoreGenericType.class) || type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}
			gson().toJson(object, jsonType, writer);
		} finally {
			writer.close();
		}
	}

	private Gson gson() {
		if (gson == null) {
			GsonBuilder gsonBuilder = applicationContext.getBean(GsonBuilder.class);
			gson = gsonBuilder.create();
		}
		return gson;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}