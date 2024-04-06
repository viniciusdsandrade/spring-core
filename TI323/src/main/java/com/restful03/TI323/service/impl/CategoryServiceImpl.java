package com.restful03.TI323.service.impl;

import com.restful03.TI323.entity.Category;
import com.restful03.TI323.repository.CategoryRepository;
import com.restful03.TI323.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category update(Long id, Category category) {
        Category categoryToUpdate = categoryRepository.getReferenceById(id);

        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());

        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
