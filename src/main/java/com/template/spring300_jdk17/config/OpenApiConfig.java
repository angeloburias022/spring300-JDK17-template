package com.template.spring300_jdk17.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * Returns an OpenAPI object that defines the structure of the API, including
     * title, version, security scheme, and security requirement. The security
     * scheme is defined as a bearer token with the name "Authorization", and the
     * security requirement is set to require this scheme.
     *
     * @return an OpenAPI object
     */
    @Bean
    public OpenAPI customOpenAPI() {
      
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT") 
                .name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(new Info().title("Template Project in Springboot 3.0.0 JDK 17").version("1.0"))
                .addSecurityItem(securityRequirement)
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", securityScheme));
    }
}
