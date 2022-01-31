package com.food.logiq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * This class will be used to pick swagger properties values from property files
 * {@link Data @Data},{@link Configuration @Configuration},
 *
 * @author Rotation5
 */
@Data
@Configuration("swaggerConfigProperties")
public class SwaggerConfigProperties {

    @Value("${api.version}")
    private String apiVersion;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.contact}")
    private String contact;
}
