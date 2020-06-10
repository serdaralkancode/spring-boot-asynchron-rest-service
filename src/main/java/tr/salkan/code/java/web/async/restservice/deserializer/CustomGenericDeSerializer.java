package tr.salkan.code.java.web.async.restservice.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.LoggerFactory;
import tr.salkan.code.java.web.async.restservice.entity.EntityExampleObject;

import java.io.IOException;


public class CustomGenericDeSerializer extends StdDeserializer<Object> implements ContextualDeserializer {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomGenericDeSerializer.class);

    private Object entityObject;
    private Class<?> targetObject;

    protected CustomGenericDeSerializer() {
        super(Object.class);

    }

    public CustomGenericDeSerializer(Object entityObject) {
        super(Object.class);
        this.entityObject = entityObject;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
            throws JsonMappingException {

        if (property.getType() != null) {
            if (property.getType().getRawClass() != null) {
                targetObject = property.getType().getRawClass();
            }
        }

        entityObject = targetObject;

        return new CustomGenericDeSerializer(targetObject);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {


        try {

            ObjectCodec oc = jsonParser.getCodec();
            JsonNode node = oc.readTree(jsonParser);

            if (entityObject.equals(EntityExampleObject.class)) {
                EntityExampleObject entityExampleObject = new EntityExampleObject();

                if (node.get("id") != null) {
                    entityExampleObject.setId(node.get("id").longValue());
                }

                if (node.get("name") != null) {
                    entityExampleObject.setName(node.get("name").asText());
                }

                return entityExampleObject;
            }

        }
        catch (Exception ex) {

            LOGGER.error("CustomGenericDeSerializer exception : " + ex);
        return null;
    }
        return null;

    }

}