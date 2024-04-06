package com.restful03.TI323.dto.product;

import com.restful03.TI323.dto.category.DadosListagemCategory;
import com.restful03.TI323.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Schema(name = "DadosListagemProduct",
        description = "Representa os dados necessários para listar um produto")
public record DadosListagemProduct(
        String name,
        BigDecimal price,
        Boolean available,
        List<DadosListagemCategory> categories
) {
    public DadosListagemProduct(Product product) {
        this(
                product.getName(),
                product.getPrice(),
                product.getAvailable(),
                product.getCategories()
                        .stream()
                        .map(DadosListagemCategory::new)
                        .collect(Collectors.toList())
        );
    }
}