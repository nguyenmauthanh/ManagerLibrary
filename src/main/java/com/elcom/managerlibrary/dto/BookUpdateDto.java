package com.elcom.managerlibrary.dto;

import com.elcom.managerlibrary.model.BookStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookUpdateDto {

    private Long id;

    @NotNull
    private String bookName;

    @NotNull
    private String description;

    @NotNull
    private Long authorId;

    private BookStatus bookStatus;

    private AuthorDtoForOneEntity author;

}
