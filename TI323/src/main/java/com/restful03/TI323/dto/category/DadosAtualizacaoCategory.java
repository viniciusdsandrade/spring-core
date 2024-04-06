package com.restful03.TI323.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "DadosAtualizacaoCategory",
        description = "Representa os dados necessários para atualizar uma categoria")
public record DadosAtualizacaoCategory(
        @NotNull(message = "ID da categoria não pode ser nulo")
        Long id,

        @NotBlank(message = "Nome da categoria não pode ser vazio")
        @Column(unique = true)
        String name,

        @NotBlank(message = "Descrição da categoria não pode ser vazia")
        String description
) {
}
