package com.elcom.managerlibrary.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDtoForOneEntity {

    private Long id;

    @NotNull
    private String categoryName;
}
