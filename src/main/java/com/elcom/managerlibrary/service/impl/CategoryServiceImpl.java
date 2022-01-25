package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Category;
import com.elcom.managerlibrary.repository.CategoryRepository;
import com.elcom.managerlibrary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long theId) {
        return categoryRepository.findById(theId).orElseThrow(
                () -> new NotFoundException(String.format("Category has id %d not found", theId))
        );
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long theId) {
        categoryRepository.deleteById(theId);
    }
}
