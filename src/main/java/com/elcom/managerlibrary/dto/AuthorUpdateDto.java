package com.elcom.managerlibrary.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AuthorUpdateDto implements Serializable {

    private Long id;

    @NotNull
    private String authorName;

    private String description;

    @NotNull
    private String email;

}
