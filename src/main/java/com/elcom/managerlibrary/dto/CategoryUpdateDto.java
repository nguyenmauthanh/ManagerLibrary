package com.elcom.managerlibrary.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CategoryUpdateDto implements Serializable {
    private Long id;

    @NotNull
    private String categoryName;


}
