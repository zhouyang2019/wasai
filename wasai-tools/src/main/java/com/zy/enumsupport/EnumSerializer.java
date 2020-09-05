package com.zy.enumsupport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EnumSerializer extends JsonSerializer<Enum> {

    private boolean isSerializeByText = true;

    public EnumSerializer() {
    }

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value instanceof BaseEnum && isSerializeByText) {
            gen.writeString(((BaseEnum) value).getText());
        } else {
            gen.writeString(value.name());
        }
    }

}
