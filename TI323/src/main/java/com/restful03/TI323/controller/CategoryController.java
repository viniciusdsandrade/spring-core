package com.restful03.TI323.controller;

import com.restful03.TI323.dto.category.DadosAtualizacaoCategory;
import com.restful03.TI323.dto.category.DadosCadastroCategory;
import com.restful03.TI323.dto.category.DadosDetalhamentoCategory;
import com.restful03.TI323.dto.category.DadosListagemCategory;
import com.restful03.TI323.entity.Category;
import com.restful03.TI323.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController("CategoryController")
@RequestMapping("/api/v1/categories")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Cadastrar uma nova categoria",
            description = "Cadastra uma nova categoria na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "409", description = "Categoria já cadastrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
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

    @Operation(
            summary = "Listar categorias",
            description = "Lista as categorias cadastradas na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
    @GetMapping
    public ResponseEntity<Page<DadosListagemCategory>> list(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<DadosListagemCategory> categories = categoryService.listar(pageable);
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Detalhar categoria",
            description = "Detalha uma categoria cadastrada na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria detalhada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategory> detailById(@PathVariable Long id) {
        DadosDetalhamentoCategory category = categoryService.buscarPorId(id);
        return ResponseEntity.ok(category);
    }

    @Operation(
            summary = "Atualizar categoria",
            description = "Atualiza uma categoria cadastrada na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
    @Transactional
    @PutMapping
    public ResponseEntity<DadosDetalhamentoCategory> update(@RequestBody DadosAtualizacaoCategory category) {
        Category categoryUpdated = categoryService.atualizar(category);
        return ResponseEntity.ok(new DadosDetalhamentoCategory(categoryUpdated));
    }

    @Operation(
            summary = "Deletar categoria",
            description = "Deleta uma categoria cadastrada na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
