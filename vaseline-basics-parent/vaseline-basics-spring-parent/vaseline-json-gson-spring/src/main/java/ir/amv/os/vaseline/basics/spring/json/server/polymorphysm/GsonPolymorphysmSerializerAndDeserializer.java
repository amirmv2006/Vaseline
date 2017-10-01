package ir.amv.os.vaseline.basics.spring.json.server.polymorphysm;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.List;

public class GsonPolymorphysmSerializerAndDeserializer implements
		JsonDeserializer<Object>, JsonSerializer<Object> {

    private List<Class<?>> allParentClasses;

    @Override
	public Object deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		if (json instanceof JsonObject) {
			JsonObject jsonObject = (JsonObject) json;
			try {
				JsonElement jsonElement = jsonObject.get("typeOfT");
				typeOfT = Class.forName(jsonElement.getAsString());
				return context.deserialize(jsonObject, typeOfT);
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public JsonElement serialize(Object src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonElement serialized = context.serialize(src);
		Class<? extends Object> srcClass = src.getClass();
		String className = srcClass.getName();
		while (srcClass != null) {
			if (allParentClasses.contains(srcClass) && serialized instanceof JsonObject) {
				JsonObject jsonObject = (JsonObject) serialized;
				jsonObject.add("typeOfT", new JsonPrimitive(className));
			}
			srcClass = srcClass.getSuperclass();
		}
		return serialized;
	}

    public void setAllParentClasses(List<Class<?>> allParentClasses) {
        this.allParentClasses = allParentClasses;
    }
}
