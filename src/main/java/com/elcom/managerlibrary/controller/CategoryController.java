package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Category;
import com.elcom.managerlibrary.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Category>> getAllCategorys(){
        List<Category> listCategory = categoryService.findAll();
        return new ResponseEntity<>(listCategory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("{theId}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long theId){
        Category category = categoryService.findById(theId);
        if(category == null){
            throw new NotFoundException(String.format("Category has id %d not found", theId));
        }
        else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @DeleteMapping("{theId}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long theId){
        Category category = categoryService.findById(theId);
        if(category == null){
            throw new NotFoundException(String.format("Category has id %d not found", theId));
        }
        else {
            categoryService.deleteById(theId);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
