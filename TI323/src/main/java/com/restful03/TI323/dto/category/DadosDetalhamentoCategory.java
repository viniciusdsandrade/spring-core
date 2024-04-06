package com.restful03.TI323.dto.category;

import com.restful03.TI323.dto.product.DadosListagemProdutosDeUmaCategoria;
import com.restful03.TI323.entity.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoCategory(
        Long id,
        String name,
        String description,
        List<DadosListagemProdutosDeUmaCategoria> products,
        LocalDateTime dataCreated,
        LocalDateTime lastUpdated
) {
    public DadosDetalhamentoCategory(Category category) {
        this(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getProducts()
                        .stream()
                        .map(DadosListagemProdutosDeUmaCategoria::new)
                        .collect(Collectors.toList()),
                category.getDataCreated(),
                category.getLastUpdated()
        );
    }
}
