package com.upk.diploma.config.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "app.services")
public class ExternalServicesProperties {

    @NotNull
    private String customerServiceUrl;

    @NotNull
    private String orderServiceUrl;

    @NotNull
    private String catalogServiceUrl;
}
