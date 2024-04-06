package com.restful03.TI323.dto.category;

import com.restful03.TI323.entity.Category;

public record DadosListagemCategory(
        String name
) {
    public DadosListagemCategory(Category category) {
        this(category.getName());
    }
}
