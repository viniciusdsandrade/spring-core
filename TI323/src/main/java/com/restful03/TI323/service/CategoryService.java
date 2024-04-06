package com.restful03.TI323.service;

import com.restful03.TI323.dto.category.DadosAtualizacaoCategory;
import com.restful03.TI323.dto.category.DadosCadastroCategory;
import com.restful03.TI323.dto.category.DadosDetalhamentoCategory;
import com.restful03.TI323.dto.category.DadosListagemCategory;
import com.restful03.TI323.entity.Category;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    @Transactional
    Category cadastrar(@Valid DadosCadastroCategory dadosCadastroCategory);

    DadosDetalhamentoCategory buscarPorId(Long id);

    Page<DadosListagemCategory> listar(Pageable pageable);

    @Transactional
    Category atualizar(@Valid DadosAtualizacaoCategory dadosCadastroCategory);

    @Transactional
    void deletar(Long id);
}
