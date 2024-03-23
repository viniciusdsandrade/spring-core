package com.restful.studentmanagement;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Classe principal da aplicação Spring Boot para gerenciamento de estudantes.
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Student Management API",
                version = "1.0.0",
                description = "API para gerenciamento de estudantes.",
                contact = @Contact(
                        name = "Vinícius Andrade",
                        email = "viniciusdsandrade0662@gmail.com",
                        url = "https://www.linkedin.com/in/viniciusdsandrade/"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Documentação do projeto",
                url = "https://github.com/viniciusdsandrade/spring-start/tree/main/udemy-spring-6-spring-boot-3-(5-projects)/student-management"
        )
)
public class StudentManagementApplication {

    /**
     * Método de inicialização da aplicação Spring Boot.
     *
     * @param args Argumentos de linha de comando fornecidos durante a inicialização.
     */
    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    /**
     * Configuração de um bean ModelMapper para mapeamento de objetos.
     *
     * @return Instância de ModelMapper configurada.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}