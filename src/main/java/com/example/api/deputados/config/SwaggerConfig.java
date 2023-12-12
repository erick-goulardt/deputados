package com.example.api.deputados.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("deverickgoulardt@gmail.com");
        contact.setName("Erick Goulardt");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API dos Deputados - Érick Goulardt")
                .version("1.0")
                .contact(contact)
                .description("Usando essa API fracassada aqui")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}