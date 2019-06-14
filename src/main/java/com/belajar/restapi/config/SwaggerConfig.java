package com.belajar.restapi.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
/**
 * This is a Javadoc comment
 * @param <T> the parameter of the class
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * This is a Javadoc comment
     * @return Docket untuk konfigurasi data
     * konfigurasi semua setting berada pada Docket disini menggunakan swagger2
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error/*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "M Zulfikar Mey Lendra",
                "https://github.com/fikar46",
                "zulfikar@doku.com");
        return new ApiInfo(
                "Building Rest API dengan SpringBoot Retail Industry",
                "Ini adalah implementasi swagger pada Rest Spring Boot",
                "Version 1.0.0",
                "Diperuntukan untuk belajar siapapun bebas download",
                contact,
                "fikar46",
                "https://github.com/fikar46",
                Collections.emptyList());
    }
}
