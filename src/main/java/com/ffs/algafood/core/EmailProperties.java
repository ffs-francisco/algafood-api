package com.ffs.algafood.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("application.mail")
public class EmailProperties {

    @NotBlank
    private String sender;
    private Sandbox sandbox = new Sandbox();

    @Getter
    @Setter
    public class Sandbox {

        @NotBlank
        public String recipient;
    }
}
