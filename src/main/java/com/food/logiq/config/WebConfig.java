package com.food.logiq.config;


import com.food.logiq.interceptor.APIInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration class for configure interceptor and resource handler
 * {@link Configuration @Configuration},{@link EnableWebMvc @EnableWebMvc},
 *
 * @author Rotation5
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Instance variables
     */
    private final APIInterceptor eventInterceptor;

    /**
     * Create a new {@code WebConfig} with the given eventInterceptor class instance.
     *
     * @param eventInterceptor the {@link APIInterceptor @EventInterceptor} class instance
     */
    public WebConfig(APIInterceptor eventInterceptor) {
        this.eventInterceptor = eventInterceptor;
    }

    /**
     * this method is used to configure interceptor
     *
     * @param registry the {@link InterceptorRegistry @InterceptorRegistry}.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(eventInterceptor).excludePathPatterns(
                "/**/*-swagger*/**",
                "/**/swagger-resources/**",
                "/**/*.html",
                "/**/error/**",
                "/**/actuator/**");
    }

    /**
     * this method is used to adding resources in resource handler
     *
     * @param registry the {@link ResourceHandlerRegistry @ResourceHandlerRegistry}.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
