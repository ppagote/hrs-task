package com.uk.hrstask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                // .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //.apis(RequestHandlerSelectors.basePackage("com.uk.hrstask"))
                .paths(PathSelectors.any())
                .build();
    }

}
