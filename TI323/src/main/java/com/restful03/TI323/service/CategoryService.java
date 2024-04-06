package com.restful03.TI323.service;

import com.restful03.TI323.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    Category findById(Long id);

    Category update(Long id, Category category);

    void deleteById(Long id);
}
