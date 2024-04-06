package com.restful03.TI323.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "DadosCadastroCategory",
        description = "Representa os dados necessários para cadastrar uma categoria")
public record DadosCadastroCategory(
        @NotBlank(message = "Nome da categoria não pode ser vazio")
        @Column(unique = true)
        String name,

        @NotBlank(message = "Descrição da categoria não pode ser vazia")
        String description
) {
}
