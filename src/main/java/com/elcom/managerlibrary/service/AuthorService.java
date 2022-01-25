package com.elcom.managerlibrary.service;

import com.elcom.managerlibrary.model.Author;
import com.elcom.managerlibrary.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> findAll();

    public Author findById(Long theId);

    public void save(Author theAuthor);

    public void deleteById(Long theId);

    Optional<Author> getAuthor(Long authorId);
}
