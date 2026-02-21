package com.example.SmartAgriConnect.security;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI SmartAgriConnect(){
        return new OpenAPI()
                .info(new Info()
                        .title("SmartAgriConnect API")
                        .description("plateforme de gestion agricole")
                        .version("v1.0.0")


                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")


                                )
                );

    }

   @Bean
    public GroupedOpenApi authApi(){
        return GroupedOpenApi.builder()
                .group("AUTH")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi agriculteurApi(){

        return GroupedOpenApi.builder()
                .group("AGRICULTEUR")
                .pathsToMatch("/api/agriculteur/**", "/api/produits/**", "/api/commandes/**","/api/stocks/**")
                .build();

    }
    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi.builder()
                .group("ADMIN")
                .pathsToMatch("/api/admin/**")
                .build();
    }




}
