package com.elcom.managerlibrary.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryDto implements Serializable {

    private Long id;

    private String categoryName;

    private List<BookDto> bookList;
}
