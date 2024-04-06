package com.restful03.TI323.controller;

import com.restful03.TI323.dto.product.DadosAtualizacaoProduct;
import com.restful03.TI323.dto.product.DadosCadastroProduct;
import com.restful03.TI323.dto.product.DadosDetalhamentoProduct;
import com.restful03.TI323.dto.product.DadosListagemProduct;
import com.restful03.TI323.entity.Product;
import com.restful03.TI323.service.ProductService;
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

@RestController("ProductController")
@RequestMapping("/api/v1/products")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Cadastrar um novo produto",
            description = "Cadastra um novo produto na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
                    @ApiResponse(responseCode = "409", description = "Produto já cadastrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
    )
    @PostMapping
    public ResponseEntity<DadosDetalhamentoProduct> create(
            @RequestBody @Valid DadosCadastroProduct dadosCadastroProduct,
            UriComponentsBuilder uriBuilder
    ) {
        Product product = productService.cadastrar(dadosCadastroProduct);
        URI uri = uriBuilder.path("/api/v1/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduct(product));
    }

    @Operation(
            summary = "Listar produtos",
            description = "Lista os produtos cadastrados na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            }
    )
    @GetMapping
    public ResponseEntity<Page<DadosListagemProduct>> list(
            @PageableDefault(size = 5, sort = {"name"}) Pageable pageable
    ) {
        Page<DadosListagemProduct> products = productService.listar(pageable);
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Detalhar produto",
            description = "Detalha um produto específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto detalhado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduct> detailById(@PathVariable Long id) {
        Product product = productService.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoProduct(product));
    }

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza as informações de um produto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            }
    )
    @Transactional
    @PutMapping
    public ResponseEntity<DadosDetalhamentoProduct> update(@RequestBody @Valid DadosAtualizacaoProduct dadosCadastroProduct) {
        Product product = productService.atualizar(dadosCadastroProduct);
        return ResponseEntity.ok(new DadosDetalhamentoProduct(product));
    }

    @Operation(
            summary = "Desativar produto",
            description = "Desativa um produto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto desativado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            }
    )
    @Transactional
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        productService.desativar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Ativar produto",
            description = "Ativa um produto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto ativado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            }
    )
    @Transactional
    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        productService.ativar(id);
        return ResponseEntity.ok().build();
    }
}
