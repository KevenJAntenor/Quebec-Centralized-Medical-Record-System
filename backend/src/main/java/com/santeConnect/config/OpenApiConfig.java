package com.santeConnect.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI medicalFileDatabaseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Medical File REST API")
                        .description("Medical Files available")
                        .version("1.0"));
    }
}
