package com.smarthome.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Smart Home Installation Inventory API")
                    .description("API for managing smart home installations, customers, and devices")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("Smart Home Team")
                            .email("support@smarthome.com")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0")
                    )
            )
            .addServersItem(
                Server()
                    .url("/")
                    .description("Local server")
            )
    }
}