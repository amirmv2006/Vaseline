package ir.amv.os.vaseline.basics.spring.json.server.converter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Writer;
import java.lang.reflect.Type;

/**
 * @author Amir
 */
public class VaselineJsonConverterGsonImpl
        implements IVaselineJsonConverter {

    private Gson gson;

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public void toJson(final Object object, final Type typeOfSource, final Writer writer) {
        gson.toJson(object, typeOfSource, writer);
    }

    @Override
    public <T> T fromJson(String json, Type objClass) {
        return gson.fromJson(json, objClass);
    }

    @Override
    public String getSubTree(final String json, final String attribute) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        JsonElement child = jsonObject.getAsJsonObject(attribute);
        return child.toString();
    }

    @Autowired
    public void setGson(final Gson gson) {
        this.gson = gson;
    }
}
