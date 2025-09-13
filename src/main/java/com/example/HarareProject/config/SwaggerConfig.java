package com.example.HarareProject.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Harareproject"))
                .addSecurityItem(new SecurityRequirement().addList("Harareproject"))
                .components(new Components().addSecuritySchemes("Harareproject", new SecurityScheme()
                        .name("Harareproject").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}