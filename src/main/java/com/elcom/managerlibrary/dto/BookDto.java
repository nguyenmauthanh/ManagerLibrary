package com.elcom.managerlibrary.dto;

import com.elcom.managerlibrary.model.BookStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BookDto implements Serializable {
    private Long id;

    @NotNull
    private String bookName;

    @NotNull
    private String description;

    private BookStatus bookStatus;

    @NotNull
    private Long authorId;

    private AuthorDtoForOneEntity author;

    private CategoryDtoForOneEntity category;

}
