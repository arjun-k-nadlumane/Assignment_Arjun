package com.example.expense_tracker.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    @Value("${myapp.openapi.dev-url}")
    private String devUrl;

    @Value("${myapp.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("test@gmail.com");
        contact.setName("TestUser");
        contact.setUrl("https://www.api.com");

        License mitLicense = new License().name("ABC Company License").url("https://choosealicense.com/licenses/lic1/");

        Info info = new Info()
            .title("Expense Tracker Management API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.test.com/terms")
            .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
