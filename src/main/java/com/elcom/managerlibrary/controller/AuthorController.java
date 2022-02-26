package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.dto.AuthorDto;
import com.elcom.managerlibrary.dto.AuthorUpdateDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/authors")

public class AuthorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAll() throws NotFoundException {
        List<AuthorDto> authorDtos = authorService.getAll();
        return ResponseEntity.ok(authorDtos);
    }


    @GetMapping("/find")
    public ResponseEntity<List<AuthorDto>> findAllByName(@RequestParam String name) throws NotFoundException {
        List<AuthorDto> authorDtos = authorService.findByAuthorName(name);
        return ResponseEntity.ok(authorDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getOneAuthor(@PathVariable(name = "id", required = true) Long id)
            throws NotFoundException {
        return ResponseEntity.ok(authorService.getOne(id));
    }

    @PostMapping()
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.save(authorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorUpdateDto> updateAuthor(@PathVariable(name = "id", required = true) Long id,
                                                        @Valid @RequestBody AuthorUpdateDto authorUpdateDto) throws NotFoundException {
        return ResponseEntity.ok(authorService.update(id, authorUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable(name = "id", required = true) Long id)
            throws NotFoundException {
        return ResponseEntity.ok(authorService.delete(id));
    }
}
