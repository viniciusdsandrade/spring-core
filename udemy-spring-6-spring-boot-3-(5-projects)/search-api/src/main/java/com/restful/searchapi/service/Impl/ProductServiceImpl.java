package com.restful.searchapi.service.Impl;

import com.restful.searchapi.entity.Product;
import com.restful.searchapi.repository.ProductRepository;
import com.restful.searchapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

    @Override
    public List<Product> searchProductsSQL(String query) {
        return productRepository.searchProductsSQL(query);
    }


}
