package com.elcom.managerlibrary.dto;

import com.elcom.managerlibrary.model.BookStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BookDtoForOneEntity implements Serializable {

    private Long id;

    @NotNull
    private String bookName;

    private String description;

    private BookStatus bookStatus;

    private CategoryDtoForOneEntity category;

    private Long authorId;

    private Long categoryId;
}
