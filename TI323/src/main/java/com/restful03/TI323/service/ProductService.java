package com.restful03.TI323.service;

import com.restful03.TI323.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product findById(Long id);

    Product update(Long id, Product product);

    void deleteById(Long id);
}
