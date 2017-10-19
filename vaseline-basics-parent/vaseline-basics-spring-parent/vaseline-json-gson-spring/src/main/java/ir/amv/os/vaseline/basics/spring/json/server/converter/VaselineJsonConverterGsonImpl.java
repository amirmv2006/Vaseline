package ir.amv.os.vaseline.basics.spring.json.server.converter;

import com.google.gson.Gson;
import ir.amv.os.vaseline.basics.apis.json.server.converter.IVaselineJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;

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
    public <T> T fromJson(String json, Class<T> objClass) {
        return gson.fromJson(json, objClass);
    }

    @Autowired
    public void setGson(final Gson gson) {
        this.gson = gson;
    }
}
