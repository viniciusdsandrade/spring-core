package com.restful.searchapi.service;

import com.restful.searchapi.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    List<Product> searchProducts(String query);

    List<Product> searchProductsSQL(String query);

    Product saveProduct(Product product);
}
