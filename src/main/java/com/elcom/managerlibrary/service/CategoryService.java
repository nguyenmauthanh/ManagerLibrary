package com.elcom.managerlibrary.service;

import com.elcom.managerlibrary.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();

    public Category findById(Long theId);

    public void save(Category category);

    public void deleteById(Long theId);
}
