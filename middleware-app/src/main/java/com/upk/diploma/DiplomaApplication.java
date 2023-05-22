package com.upk.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.upk.diploma", exclude = {R2dbcAutoConfiguration.class})
@ConfigurationPropertiesScan(value = "com.upk.diploma.config.properties")
public class DiplomaApplication {

    public static void main(String[] args) {
        //
        SpringApplication.run(DiplomaApplication.class, args);
    }

}
