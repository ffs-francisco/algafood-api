package com.ffs.algafood.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 *
 * @author francisco
 */
@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> page, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();

        generator.writeObjectField("content", page.getContent());

        generator.writeObjectField("size", page.getSize());
        generator.writeObjectField("number", page.getNumber());
        generator.writeObjectField("totalPages", page.getTotalPages());
        generator.writeObjectField("totalElements", page.getTotalElements());

        generator.writeEndObject();
    }
}
