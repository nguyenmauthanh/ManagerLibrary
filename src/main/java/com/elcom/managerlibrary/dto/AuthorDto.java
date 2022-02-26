package com.elcom.managerlibrary.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AuthorDto {

    private Long id;

    @NotNull
    private String authorName;

    private String description;

    @NotNull
    private String email;

    private List<BookDtoForOneEntity> books;
}
