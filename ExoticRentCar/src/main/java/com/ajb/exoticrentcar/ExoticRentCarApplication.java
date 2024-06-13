package com.ajb.exoticrentcar;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExoticRentCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExoticRentCarApplication.class, args);
    }

    /**
     * Accede desde http://localhost:8080/swagger-ui/index.html#
     * @return OpenAPI con info
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ángel Jurado Bejarano - ExoticRentCar")
                        .description("Alquiler de coches deportivos desarrollado por Ángel Jurado Bejarano")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http:springdoc.org")));
    }

}
