package com.ffs.algafood.api.ultil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffs.algafood.core.validation.ValidationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 *
 * @author francisco
 */
@Component
public class ApiUpdateUtils {

    @Autowired
    private SmartValidator smartValidator;

    public void merge(Map<String, Object> dataOrigin, Object dataDestination, HttpServletRequest request) throws HttpMessageNotReadableException {
        try {
            var objectMapper = new ObjectMapper();
            objectMapper.configure(FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, true);

            var objectOrigin = objectMapper.convertValue(dataOrigin, dataDestination.getClass());
            dataOrigin.forEach((String namePropertie, Object valPropertie) -> {
                Field field = ReflectionUtils.findField(dataDestination.getClass(), namePropertie);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, objectOrigin);
                ReflectionUtils.setField(field, dataDestination, newValue);
            });
        } catch (IllegalArgumentException ex) {
            var rootCouse = ExceptionUtils.getRootCause(ex);
            var servletRequest = new ServletServerHttpRequest(request);

            throw new HttpMessageNotReadableException(ex.getMessage(), rootCouse, servletRequest);
        }
    }

    public void validate(Object object, String objectName) throws ValidationException {
        var bindingResult = new BeanPropertyBindingResult(object, objectName);

        this.smartValidator.validate(object, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }

}
