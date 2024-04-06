package com.restful03.TI323.service.impl;

import com.restful03.TI323.entity.Product;
import com.restful03.TI323.repository.ProductRepository;
import com.restful03.TI323.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productToUpdate = productRepository.getReferenceById(id);

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());

        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
