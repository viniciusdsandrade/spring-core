package com.restful03.TI323.service.impl;

import com.restful03.TI323.dto.product.DadosAtualizacaoProduct;
import com.restful03.TI323.dto.product.DadosCadastroProduct;
import com.restful03.TI323.dto.product.DadosListagemProduct;
import com.restful03.TI323.entity.Category;
import com.restful03.TI323.entity.Product;
import com.restful03.TI323.exception.DuplicateEntryException;
import com.restful03.TI323.exception.EntityNotFoundException;
import com.restful03.TI323.repository.CategoryRepository;
import com.restful03.TI323.repository.ProductRepository;
import com.restful03.TI323.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Product cadastrar(@Valid DadosCadastroProduct dadosCadastroProduct) {
        if (productRepository.existsByName(dadosCadastroProduct.name()))
            throw new DuplicateEntryException("Produto já cadastrado");

        if (!categoryRepository.existsById(dadosCadastroProduct.categoryId()))
            throw new EntityNotFoundException("Categoria não encontrada");

        Product product = new Product(dadosCadastroProduct);
        product.addCategory(new Category(dadosCadastroProduct.categoryId()));
        productRepository.save(product);
        return product;
    }

    @Override
    @Transactional
    public Product atualizar(DadosAtualizacaoProduct dadosAtualizacaoProduct) {
        if (!productRepository.existsById(dadosAtualizacaoProduct.id()))
            throw new EntityNotFoundException("Produto não encontrado");

        Product product = productRepository.getReferenceById(dadosAtualizacaoProduct.id());
        product.atualizarInformacoes(dadosAtualizacaoProduct);
        return product;
    }

    @Override
    public void desativar(Long id) {
        if (!productRepository.existsById(id))
            throw new EntityNotFoundException("Produto não encontrado");

        Product product = productRepository.getReferenceById(id);
        product.desativarProduto();
    }

    @Override
    public void ativar(Long id) {
        if (!productRepository.existsById(id))
            throw new EntityNotFoundException("Produto não encontrado");

        Product product = productRepository.getReferenceById(id);
        product.ativarProduto();
    }

    @Override
    public Product buscarPorId(Long id) {
        if (!productRepository.existsById(id))
            throw new EntityNotFoundException("Produto não encontrado");

        return productRepository.getReferenceById(id);
    }

    @Override
    public Page<DadosListagemProduct> listar(Pageable pageable) {
        if (productRepository.count() == 0)
            throw new EntityNotFoundException("Nenhum produto encontrado");

        Page<Product> products = productRepository.findAllByAvailableTrue(pageable);
        return products.map(DadosListagemProduct::new);
    }
}
