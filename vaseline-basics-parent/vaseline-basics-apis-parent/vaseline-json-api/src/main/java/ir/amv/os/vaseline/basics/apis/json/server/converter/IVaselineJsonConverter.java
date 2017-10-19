package ir.amv.os.vaseline.basics.apis.json.server.converter;

public interface IVaselineJsonConverter {
    String toJson(Object object);

    <T> T fromJson(String json, Class<T> objClass);
}
