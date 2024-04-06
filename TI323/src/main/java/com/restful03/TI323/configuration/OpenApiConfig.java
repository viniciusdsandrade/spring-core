package com.restful03.TI323.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "TI323 RESTful API",
                version = "v1.0.0",
                description = "RESTful API for TI323",
                termsOfService = "https://github.com/viniciusdsandrade/spring-start/tree/main/TI323",
                contact = @Contact(
                        name = "Vinícius dos Santos Andrade e Arthur Assis Gonçalves",
                        email = "vinicius_andrade2010@hotmail.com",
                        url = "https://www.linkedin.com/in/viniciusdsandrade"
                )
        )
)
public class OpenApiConfig {
}
