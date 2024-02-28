package com.infelsoft.student;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.inject.Singleton;

@OpenAPIDefinition(info = @Info(title = "student-service", version = "1.0",
                                contact = @Contact(name = "student-service",
                                                   email = "verem.dugeri@gmail.com")))
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
