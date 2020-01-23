package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl.polymorphism;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JacksonPolymorphismConverter<P> extends PolymorphismConverters<P, String> {

    public static final String VASELINE_TYPE = "VASELINE_TYPE";
    private final ObjectMapper objectMapper;

    public JacksonPolymorphismConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected String to(P dest, Class<?> childType) {
        ObjectNode jsonNode = objectMapper.valueToTree(dest);
        jsonNode.put(VASELINE_TYPE, childType.getName());
        return jsonNode.toString();
    }

    @Override
    protected <C extends P> Class<C> extractType(String source) {
        try {
            JsonNode jsonNode = objectMapper.readTree(source);
            return (Class<C>) Class.forName(jsonNode.get(VASELINE_TYPE).asText());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected <C extends P> C from(String dest, Class<C> childType) {
        try {
            return objectMapper.readValue(dest, childType);
        } catch (IOException e) {
            return null;
        }
    }
}
