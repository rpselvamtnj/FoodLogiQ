package com.food.logiq.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This instance for configure swagger
 * {@link Configuration @Configuration},{@link EnableSwagger2 @EnableSwagger2},
 *
 * @author Rotation5
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Instance variables
     */
    private String groupName = "Swagger";

    /**
     * Bean creation for docket
     *
     * @param swaggerConfigProperties
     */
    @Bean
    public Docket apiDocket(SwaggerConfigProperties swaggerConfigProperties) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swaggerConfigProperties))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.food.logiq"))
                .paths(PathSelectors.any())
                .build()
                .groupName(groupName)
                .securitySchemes(apiKey())
                .securityContexts(Arrays.asList(securityContext()));
    }

    /**
     * Method for build api information from properties file
     *
     * @param swaggerConfigProperties the {@link SwaggerConfigProperties @SwaggerConfigProperties}.
     * @return the {@link ApiInfo @ApiInfo}.
     */
    private ApiInfo apiInfo(SwaggerConfigProperties swaggerConfigProperties) {
        return new ApiInfoBuilder().title(swaggerConfigProperties.getTitle()).description(swaggerConfigProperties.getDescription())
                .version(swaggerConfigProperties.getApiVersion()).contact(new Contact(swaggerConfigProperties.getContact(), "", "")).build();
    }

    /**
     * Method for create apikey for authorization
     *
     * @return the {@link ApiKey @ApiKey}.
     */
    private List<ApiKey> apiKey() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Bearer", "Authorization", "header"));
        apiKeyList.add(new ApiKey("UserId", "userId", "header"));
        return apiKeyList;
    }

    /**
     * Method for create security context using default auth
     *
     * @return the {@link SecurityContext @SecurityContext}.
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    /**
     * Method for create list of security reference
     *
     * @return the {@link List<SecurityReference> @List<SecurityReference>}.
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferenceList = new ArrayList<>();
        securityReferenceList.add(new SecurityReference("Bearer", authorizationScopes));
        securityReferenceList.add(new SecurityReference("UserId", authorizationScopes));
        return securityReferenceList;
    }
}
