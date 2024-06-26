package com.restful03.TI323.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Schema(name = "DadosCadastroProduct",
        description = "Representa os dados necessários para cadastrar um produto")
public record DadosCadastroProduct(
        @NotNull(message = "Nome do produto não pode ser nulo")
        @Column(unique = true)
        String name,

        @NotNull(message = "Descrição do produto não pode ser nula")
        String description,

        @NotNull(message = "Preço do produto não pode ser nulo")
        @PositiveOrZero(message = "Preço do produto não pode ser negativo")
        BigDecimal price,

        @NotNull(message = "Disponibilidade do produto não pode ser nula")
        Boolean available,

        @NotNull(message = "Id da categoria não pode ser nulo")
        Long categoryId
) {
}
