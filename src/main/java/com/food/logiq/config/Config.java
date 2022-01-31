package com.food.logiq.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * This config class will be used to validate the http request
 * {@link Data @Data},{@link Configuration @Configuration},
 *
 * @author Rotation5
 */
@Configuration
public class Config implements InitializingBean {

    /**
     * Instance variables
     */
    private final RequestMappingHandlerAdapter converter;

    /**
     * Create a new {@code Config} with the given config class instance.
     *
     * @param converter the {@link RequestMappingHandlerAdapter @RequestMappingHandlerAdapter} class instance
     */
    public Config(RequestMappingHandlerAdapter converter) {
        this.converter = converter;
    }

    @Override
    public void afterPropertiesSet() {
        configureJacksonToFailOnUnknownProperties();
    }

    private void configureJacksonToFailOnUnknownProperties() {
        MappingJackson2HttpMessageConverter httpMessageConverter = converter.getMessageConverters().stream()
                .filter(mc -> mc.getClass().equals(MappingJackson2HttpMessageConverter.class))
                .map(MappingJackson2HttpMessageConverter.class::cast)
                .findFirst().get();

        httpMessageConverter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}