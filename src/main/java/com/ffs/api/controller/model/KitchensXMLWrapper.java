package com.ffs.api.controller.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.ffs.api.domain.model.Kitchen;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author francisco
 */
@JacksonXmlRootElement(localName = "kitchens")
@Data
public class KitchensXMLWrapper {
    
    @JacksonXmlProperty(localName = "kitchen")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull
    private List<Kitchen> kitchens;
}
