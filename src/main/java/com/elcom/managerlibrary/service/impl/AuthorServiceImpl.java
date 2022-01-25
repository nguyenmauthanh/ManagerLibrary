package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.model.Author;
import com.elcom.managerlibrary.model.Book;
import com.elcom.managerlibrary.repository.AuthorRepository;
import com.elcom.managerlibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long theId) {
        Optional<Author> optionalAuthor = authorRepository.findById(theId);
        Author theAuthor = null;
        if(optionalAuthor.isPresent()){
            theAuthor = optionalAuthor.get();
        }
        else{
            throw new RuntimeException("Did not find author id - " + theId);
        }
        return theAuthor;
    }

    @Override
    public void save(Author theAuthor) {
        authorRepository.save(theAuthor);
    }

    @Override
    public void deleteById(Long theId) {
        authorRepository.deleteById(theId);
    }

    @Override
    public Optional<Author> getAuthor(Long authorId) {
        return authorRepository.findById(authorId);
    }
}
