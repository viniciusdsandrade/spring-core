package com.restful03.TI323.dto.product;

import com.restful03.TI323.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DadosListagemProdutosDeUmaCategoria",
        description = "Representa os dados necessários para listar um produto de uma categoria")
public record DadosListagemProdutosDeUmaCategoria(
        String name
) {
    public DadosListagemProdutosDeUmaCategoria(Product product) {
        this(product.getName());
    }
}
