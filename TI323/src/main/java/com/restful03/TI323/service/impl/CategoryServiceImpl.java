package com.restful03.TI323.service.impl;

import com.restful03.TI323.dto.category.DadosAtualizacaoCategory;
import com.restful03.TI323.dto.category.DadosCadastroCategory;
import com.restful03.TI323.dto.category.DadosDetalhamentoCategory;
import com.restful03.TI323.dto.category.DadosListagemCategory;
import com.restful03.TI323.entity.Category;
import com.restful03.TI323.exception.DuplicateEntryException;
import com.restful03.TI323.exception.EntityNotFoundException;
import com.restful03.TI323.repository.CategoryRepository;
import com.restful03.TI323.service.CategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category cadastrar(DadosCadastroCategory dadosCadastroCategory) {
        if (categoryRepository.existsByName(dadosCadastroCategory.name()))
            throw new DuplicateEntryException("Category already exists");

        Category category = new Category(dadosCadastroCategory);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public DadosDetalhamentoCategory buscarPorId(Long id) {
        if (!categoryRepository.existsById(id))
            throw new EntityNotFoundException("Category not found");

        Category category = categoryRepository.getReferenceById(id);
        return new DadosDetalhamentoCategory(category);
    }

    @Override
    public Page<DadosListagemCategory> listar(Pageable pageable) {
        if (categoryRepository.count() == 0)
            throw new EntityNotFoundException("No categories found");

        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(DadosListagemCategory::new);
    }

    @Override
    @Transactional
    public Category atualizar(@Valid DadosAtualizacaoCategory dadosCadastroCategory) {
        if (!categoryRepository.existsById(dadosCadastroCategory.id()))
            throw new EntityNotFoundException("Category not found");

        Category category = categoryRepository.getReferenceById(dadosCadastroCategory.id());
        category.atualizarInformacoes(dadosCadastroCategory);
        return category;
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!categoryRepository.existsById(id))
            throw new EntityNotFoundException("Category not found");

        categoryRepository.deleteById(id);
    }
}
