package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.dto.BookDto;
import com.elcom.managerlibrary.dto.BookUpdateDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Book;
import com.elcom.managerlibrary.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/books")
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAll() throws NotFoundException {
        return ResponseEntity.ok(bookService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getAll(@PathVariable(name = "id", required = true) Long id)
            throws NotFoundException {
        return ResponseEntity.ok(bookService.getOne(id));
    }
    @PostMapping()
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) throws Exception {
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable(name = "id", required = true) Long id)
            throws NotFoundException {
        return ResponseEntity.ok(bookService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookUpdateDto> updateBook(@PathVariable(name = "id", required = true) Long id,
                                                    @Valid @RequestBody BookUpdateDto bookUpdateDto) throws NotFoundException {
        return ResponseEntity.ok(bookService.update(id, bookUpdateDto));
    }


}
