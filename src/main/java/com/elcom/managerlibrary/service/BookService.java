package com.elcom.managerlibrary.service;

import com.elcom.managerlibrary.dto.BookDto;
import com.elcom.managerlibrary.dto.BookUpdateDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Book;

import java.util.List;

public interface BookService {
    public BookDto save(BookDto bookDto) throws Exception;
    public List<BookDto> getAll() throws NotFoundException;
    public BookUpdateDto update(Long id, BookUpdateDto bookUpdateDto) throws NotFoundException;
    public BookDto getOne(Long id) throws NotFoundException ;
    public Boolean delete(Long id) throws NotFoundException;
    public List<BookDto> SearchBooksByName(String name) throws NotFoundException;

    public Long count();
}
