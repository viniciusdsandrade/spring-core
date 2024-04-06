package com.restful03.TI323.dto.product;

import com.restful03.TI323.entity.Product;

public record DadosListagemProdutosDeUmaCategoria(
        String name
) {
    public DadosListagemProdutosDeUmaCategoria(Product product) {
        this(product.getName());
    }
}
