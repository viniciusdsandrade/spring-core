package com.restful03.TI323.controller;

import com.restful03.TI323.dto.category.DadosAtualizacaoCategory;
import com.restful03.TI323.dto.category.DadosCadastroCategory;
import com.restful03.TI323.dto.category.DadosDetalhamentoCategory;
import com.restful03.TI323.dto.category.DadosListagemCategory;
import com.restful03.TI323.entity.Category;
import com.restful03.TI323.service.CategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategory> create(
            @RequestBody @Valid DadosCadastroCategory dadosCadastroCategory,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Category category = categoryService.cadastrar(dadosCadastroCategory);
        URI uri = uriComponentsBuilder.path("/api/v1/categories/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategory(category));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCategory>> list(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<DadosListagemCategory> categories = categoryService.listar(pageable);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategory> detailById(@PathVariable Long id) {
        DadosDetalhamentoCategory category = categoryService.buscarPorId(id);
        return ResponseEntity.ok(category);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DadosDetalhamentoCategory> update(@RequestBody DadosAtualizacaoCategory category) {
        Category categoryUpdated = categoryService.atualizar(category);
        return ResponseEntity.ok(new DadosDetalhamentoCategory(categoryUpdated));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
