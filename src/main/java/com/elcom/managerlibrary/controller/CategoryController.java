package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.dto.CategoryDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Category;
import com.elcom.managerlibrary.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/categorys")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategorys() throws NotFoundException{
        List<CategoryDto> listCategory = categoryService.getAll();
        return ResponseEntity.ok(listCategory);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) throws Exception{
        categoryService.save(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("{Id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long Id){
        CategoryDto category = categoryService.findById(Id);
        if(category == null){
            throw new NotFoundException(String.format("Category has id %d not found", Id));
        }
        else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @DeleteMapping("{theId}")
    public ResponseEntity<CategoryDto> deleteCategoryById(@PathVariable Long theId){
        CategoryDto category = categoryService.findById(theId);
        if(category == null){
            throw new NotFoundException(String.format("Category has id %d not found", theId));
        }
        else {
            categoryService.delete(theId);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
