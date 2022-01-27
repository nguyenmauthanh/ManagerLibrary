package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Book;
import com.elcom.managerlibrary.model.User;
import com.elcom.managerlibrary.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> listBook = bookService.findAll();
        return new ResponseEntity<>(listBook, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("{theId}")
    public ResponseEntity<Book> deleteById(@PathVariable Long theId){
        Book book = bookService.findById(theId);
        bookService.deleteById(theId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("{theId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long theId, @RequestBody Book book){
        Book theBook = bookService.findById(theId);
        if(theBook == null){
            throw new NotFoundException(String.format("Book has ID %d not found",theId));
        }
        theBook.setBookName(book.getBookName());
        theBook.setAuthor(book.getAuthor());
        theBook.setDescription(book.getDescription());
        theBook.setCategory(book.getCategory());
        return new ResponseEntity<>(theBook, HttpStatus.OK);
    }


}
