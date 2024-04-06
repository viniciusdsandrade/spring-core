package com.restful03.TI323.dto.product;

import com.restful03.TI323.dto.category.DadosListagemCategory;
import com.restful03.TI323.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Schema(name = "DadosDetalhamentoProduct",
        description = "Representa os dados detalhados de um produto")
public record DadosDetalhamentoProduct(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Boolean available,
        List<DadosListagemCategory> categories,
        LocalDateTime dataCreated,
        LocalDateTime lastUpdated
) {
    public DadosDetalhamentoProduct(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable(),
                product.getCategories()
                        .stream()
                        .map(DadosListagemCategory::new)
                        .collect(Collectors.toList()),
                product.getDataCreated(),
                product.getLastUpdated()
        );
    }
}
