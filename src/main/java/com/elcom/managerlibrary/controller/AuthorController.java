package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Author;
import com.elcom.managerlibrary.model.Book;
import com.elcom.managerlibrary.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")

public class AuthorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> listAuthor = authorService.findAll();
        return new ResponseEntity<>(listAuthor, HttpStatus.OK);
    }

    @GetMapping("{theId}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Long theId){
        Author author = authorService.findById(theId);
        if (author == null){
            throw new NotFoundException(String.format("Author has id %d not found", theId));
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        authorService.save(author);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("{theId}")
    public ResponseEntity<Author> deleteAuthorById(@PathVariable Long theId){
        Author author = authorService.findById(theId);
        if (author == null){
            throw new NotFoundException(String.format("Author has id %d not found", theId));
        }
        else {
            authorService.deleteById(theId);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping("/theId")
    public ResponseEntity<Author> updateBook(@PathVariable Long theId, @RequestBody Author author){
        Author theAuthor = authorService.findById(theId);
        if(theAuthor == null){
            throw new NotFoundException(String.format("Book has ID %d not found",theId));
        }
        theAuthor.setAuthorName(author.getAuthorName());
        theAuthor.setDescription(author.getAuthorName());
        theAuthor.setBooksList(author.getBooksList());
        return new ResponseEntity<>(theAuthor, HttpStatus.OK);
    }
}
