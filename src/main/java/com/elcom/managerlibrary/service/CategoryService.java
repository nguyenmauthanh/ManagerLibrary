package com.elcom.managerlibrary.service;

import com.elcom.managerlibrary.dto.CategoryDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Category;

import javax.validation.Valid;
import java.util.List;

public interface CategoryService {
    public CategoryDto save(@Valid CategoryDto categoryDto) throws Exception;
    public List<CategoryDto> getAll() throws NotFoundException;
    public CategoryDto findById(Long id) throws NotFoundException;
    public CategoryDto update(Long id, @Valid CategoryDto categoryDto) throws Exception;
    public Boolean delete(Long id) throws NotFoundException;

}
