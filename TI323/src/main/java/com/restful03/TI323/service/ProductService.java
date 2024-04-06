package com.restful03.TI323.service;

import com.restful03.TI323.dto.product.DadosAtualizacaoProduct;
import com.restful03.TI323.dto.product.DadosCadastroProduct;
import com.restful03.TI323.dto.product.DadosDetalhamentoProduct;
import com.restful03.TI323.dto.product.DadosListagemProduct;
import com.restful03.TI323.entity.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    @Transactional
    Product cadastrar(@Valid DadosCadastroProduct dadosCadastroProduct);

    @Transactional
    Product atualizar(@Valid DadosAtualizacaoProduct dadosAtualizacaoProduct);

    @Transactional
    void desativar(Long id);

    @Transactional
    void ativar(Long id);

    Product buscarPorId(Long id);

    Page<DadosListagemProduct> listar(Pageable pageable);
}
