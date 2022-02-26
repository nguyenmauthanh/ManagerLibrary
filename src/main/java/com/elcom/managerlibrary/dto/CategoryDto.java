package com.elcom.managerlibrary.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;

    private String categoryName;

    private List<BookDto> bookList;
}
