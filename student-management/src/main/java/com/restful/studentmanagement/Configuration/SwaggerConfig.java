package com.restful.studentmanagement.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger para documentação da API Student Management.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configuração do OpenAPI para a API Student Management.
     *
     * @return Instância de OpenAPI configurada.
     */
    @Bean
    OpenAPI springStudentManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .description("API para gerenciamento de estudantes")
                        .version("1.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org"))
                        .contact(new Contact()
                                .name("Student Management API Support")
                                .url("https://github.com/viniciusdsandrade/spring-start/tree/main/udemy-spring-6-spring-boot-3-(5-projects)/student-management")
                                .email("vinicius_andrade2010@hotmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/viniciusdsandrade;"));
    }

    /**
     * Customizador global para adicionar respostas padronizadas em todas as operações.
     *
     * @return Instância de OpenApiCustomizer configurada.
     */
    @Bean
    OpenApiCustomizer customGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
                apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
                apiResponses.addApiResponse("405", createApiResponse("Método Não Permitido!"));
                apiResponses.addApiResponse("422", createApiResponse("Erro de Validação!"));
                apiResponses.addApiResponse("429", createApiResponse("Limite de Requisições Excedido!"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
                apiResponses.addApiResponse("503", createApiResponse("Serviço Indisponível!"));
            }));
        };
    }

    /**
     * Cria uma resposta padronizada para ser usada nas operações.
     *
     * @param description Descrição da resposta.
     * @return Instância de ApiResponse configurada.
     */
    private ApiResponse createApiResponse(String description) {
        return new ApiResponse().description(description);
    }
}
