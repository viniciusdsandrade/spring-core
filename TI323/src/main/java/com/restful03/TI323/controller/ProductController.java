package com.restful03.TI323.controller;

import com.restful03.TI323.dto.product.DadosAtualizacaoProduct;
import com.restful03.TI323.dto.product.DadosCadastroProduct;
import com.restful03.TI323.dto.product.DadosDetalhamentoProduct;
import com.restful03.TI323.dto.product.DadosListagemProduct;
import com.restful03.TI323.entity.Product;
import com.restful03.TI323.service.ProductService;
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
@RequestMapping("/api/v1/products")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoProduct> create(
            @RequestBody @Valid DadosCadastroProduct dadosCadastroProduct,
            UriComponentsBuilder uriBuilder
    ) {
        Product product = productService.cadastrar(dadosCadastroProduct);
        URI uri = uriBuilder.path("/api/v1/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduct(product));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduct>> list(
            @PageableDefault(size = 5, sort = {"name"}) Pageable pageable
    ) {
        Page<DadosListagemProduct> products = productService.listar(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduct> detailById(@PathVariable Long id) {
        Product product = productService.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoProduct(product));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DadosDetalhamentoProduct> update(@RequestBody @Valid DadosAtualizacaoProduct dadosCadastroProduct) {
        Product product = productService.atualizar(dadosCadastroProduct);
        return ResponseEntity.ok(new DadosDetalhamentoProduct(product));
    }

    @Transactional
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        productService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        productService.ativar(id);
        return ResponseEntity.noContent().build();
    }
}
