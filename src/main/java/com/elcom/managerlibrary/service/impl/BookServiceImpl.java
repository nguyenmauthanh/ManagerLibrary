package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Book;
import com.elcom.managerlibrary.repository.BookRepository;
import com.elcom.managerlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long theId) {

        return bookRepository.findById(theId).orElseThrow(() ->
                new NotFoundException(String.format("Not found book has id %d", theId)));
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public void deleteById(Long theId) {
        bookRepository.deleteById(theId);
    }

    @Override
    public List<Book> findBookByName(String nameBook) {
        return bookRepository.findByName(nameBook);
    }
}
