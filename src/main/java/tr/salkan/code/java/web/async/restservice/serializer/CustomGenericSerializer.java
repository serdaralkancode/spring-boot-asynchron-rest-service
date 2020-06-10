package tr.salkan.code.java.web.async.restservice.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import tr.salkan.code.java.web.async.restservice.entity.EntityExampleObject;

import java.io.IOException;

public class CustomGenericSerializer extends StdSerializer<Object> implements ContextualSerializer{

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomGenericSerializer.class);

    private Object entityObject;
    private Class<?> targetObject;

    public CustomGenericSerializer(Object entityObject) {
        super(Object.class);
        this.entityObject = entityObject;
    }

    protected CustomGenericSerializer() {
        super(Object.class);

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {

        if(property.getType() != null)
        {
            if(property.getType().getRawClass() != null)
            {
                targetObject = property.getType().getRawClass();
            }

        }

        entityObject = targetObject;
        return new CustomGenericSerializer(targetObject);
    }

    @Override
    public void serialize(Object value, JsonGenerator json, SerializerProvider provider) throws IOException {

        try {
            if (value != null) {
                if (value.getClass() != null) {
                    if (value.getClass().equals(EntityExampleObject.class)) {
                        EntityExampleObject entityExampleObject = (EntityExampleObject) value;

                        json.writeStartObject();

                        if (entityExampleObject.getId() != null) {
                            json.writeNumberField("id", entityExampleObject.getId());
                        }

                        if (!StringUtils.isBlank(entityExampleObject.getName())) {
                            json.writeStringField("name", entityExampleObject.getName());
                        }
                        json.writeEndObject();
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("CustomGenericSerializer exception : " + e);
        }

    }
}
