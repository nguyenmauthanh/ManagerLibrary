package com.elcom.managerlibrary.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthorDtoForOneEntity {
    private Long id;

    @NotNull
    private String authorName;

    private String description;

    @NotNull
    private String email;

}
