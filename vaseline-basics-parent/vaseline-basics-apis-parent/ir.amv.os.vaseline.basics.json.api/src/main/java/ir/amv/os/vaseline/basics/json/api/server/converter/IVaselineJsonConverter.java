package ir.amv.os.vaseline.basics.json.api.server.converter;

import java.io.Writer;
import java.lang.reflect.Type;

public interface IVaselineJsonConverter {
    String toJson(Object object);
    void toJson(Object object, Type typeOfSource, Writer writer);

    <T> T fromJson(String json, Type objClass);

    String getSubTree(String json, String attribute);
}
