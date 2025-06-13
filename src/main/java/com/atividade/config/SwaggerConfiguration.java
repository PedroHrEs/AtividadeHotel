package com.atividade.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("Hotel")
                .pathsToMatch("/**")
                .packagesToScan("com.atividade.resources")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("Hotel")
                .description("Hotel")
                .version("1.0")
                .contact(new Contact().name("Curso Spring")
                        .url("https://github.com/curso-spring/Curso-Spring")
                        .email("curso@curso.com.br")));
    }
}
