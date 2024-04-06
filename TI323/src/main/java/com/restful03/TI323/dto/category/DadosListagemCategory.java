package com.restful03.TI323.dto.category;

import com.restful03.TI323.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DadosListagemCategory",
        description = "Representa os dados de listagem de uma categoria")
public record DadosListagemCategory(
        String name
) {
    public DadosListagemCategory(Category category) {
        this(category.getName());
    }
}
