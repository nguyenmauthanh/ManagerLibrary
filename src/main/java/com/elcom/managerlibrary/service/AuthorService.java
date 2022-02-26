package com.elcom.managerlibrary.service;

import com.elcom.managerlibrary.dto.AuthorDto;
import com.elcom.managerlibrary.dto.AuthorUpdateDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Author;
import com.elcom.managerlibrary.model.Book;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public AuthorDto save(AuthorDto authorDto);
    public List<AuthorDto> getAll() throws NotFoundException;
    public List<AuthorDto> findByAuthorName(String authorName) throws NotFoundException ;
    public AuthorUpdateDto update(Long id, @Valid AuthorUpdateDto authorUpdateDto) throws NotFoundException;
    public AuthorDto getOne(Long id) throws NotFoundException;
    public Boolean delete(Long id) throws NotFoundException ;
}
