package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.dto.CategoryDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Category;
import com.elcom.managerlibrary.repository.CategoryRepository;
import com.elcom.managerlibrary.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) throws Exception {
        Optional<Category> listC = categoryRepository.findById(categoryDto.getId());
        if(listC.isPresent() == true){
            throw new Exception("Category Id exist " + categoryDto.getId());
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAll() throws NotFoundException {
        List<Category> categories = categoryRepository.findAll();
        if(categories.size() < 1){
            throw new NotFoundException("Category don't exist");
        }
        CategoryDto[] categoryDtos = modelMapper.map(categories, CategoryDto[].class);
        return Arrays.asList(categoryDtos);
    }

    @Override
    public CategoryDto findById(Long id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isPresent()) {
            throw new NotFoundException("Category don't exist");
        }
        return modelMapper.map(categoryOptional.get(), CategoryDto.class);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isPresent()) {
            throw new NotFoundException("Category don't exist");
        }
        categoryRepository.delete(categoryOptional.get());
        return true;
    }
}
