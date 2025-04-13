package ua.biblioteka.biblioteka_backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                        )
                )
                .info(
                        new Info().title("BIBLIOTEKA API")
                                .description("The API documentation for the BIBLIOTEKA")
                                .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecuritySchema"))
                .components(new Components().addSecuritySchemes("JavaInUseSecuritySchema", new SecurityScheme()
                        .name("JavaInUseSecuritySchema").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}
