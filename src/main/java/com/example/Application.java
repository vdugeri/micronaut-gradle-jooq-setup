package com.example;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.inject.Singleton;

@OpenAPIDefinition(
        info =
                @Info(
                        title = "micronaut-gradle-jooq-setup",
                        version = "1.0",
                        contact = @Contact(name = "micronaut-gradle-jooq-setup", email = "xxxx@xxxx.com")))
@Singleton
public class Application {

    public static void main(String[] args) {
        Micronaut.build(args)
                .mainClass(Application.class)
                .banner(false)
                .eagerInitSingletons(true)
                .start();
    }
}
