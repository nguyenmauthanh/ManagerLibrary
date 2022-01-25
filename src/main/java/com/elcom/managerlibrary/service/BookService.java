package com.elcom.managerlibrary.service;

import com.elcom.managerlibrary.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long theId);

    void save(Book theBook);

    void deleteById(Long theId);

    List<Book> findBookByName(String nameBook);
}
