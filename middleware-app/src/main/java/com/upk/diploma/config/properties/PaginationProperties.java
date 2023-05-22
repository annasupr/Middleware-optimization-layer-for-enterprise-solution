package com.upk.diploma.config.properties;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "app.pagination")
public class PaginationProperties {

    @NotNull
    private Integer defaultPageValue;
}
