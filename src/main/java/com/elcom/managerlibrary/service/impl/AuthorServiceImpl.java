package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.dto.AuthorDto;
import com.elcom.managerlibrary.dto.AuthorUpdateDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Author;
import com.elcom.managerlibrary.repository.AuthorRepository;
import com.elcom.managerlibrary.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(ModelMapper modelMapper, AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public AuthorDto save(AuthorDto authorDto) {
        Author authorChecked = authorRepository.findByEmail(authorDto.getEmail());
        if (authorChecked != null) {
            throw new IllegalArgumentException("User email already exist");
        }
        Author author = modelMapper.map(authorDto, Author.class);
        authorRepository.save(author);
        authorDto.setId(author.getId());
        return authorDto;
    }

    public List<AuthorDto> getAll() throws NotFoundException {
        List<Author> authors = authorRepository.findAll();
        if (authors.size() < 1) {
            throw new NotFoundException("Author don't already exist");
        }
        AuthorDto[] authorDtos = modelMapper.map(authors, AuthorDto[].class);

        return Arrays.asList(authorDtos);
    }

    public List<AuthorDto> findByAuthorName(String authorName) throws NotFoundException {
        List<Author> authors = authorRepository.findByAuthorName(authorName);
        if (authors.size() < 1) {
            throw new NotFoundException("Author don't already exist");
        }
        AuthorDto[] authorDtos = modelMapper.map(authors, AuthorDto[].class);

        return Arrays.asList(authorDtos);
    }

    public AuthorUpdateDto update(Long id, @Valid AuthorUpdateDto authorUpdateDto) throws NotFoundException {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (!authorOpt.isPresent()) {
            throw new NotFoundException("User dosen't exist : " + id);
        }
        Author author = modelMapper.map(authorUpdateDto, Author.class);
        author.setId(id);
        authorRepository.save(author);
        authorUpdateDto.setId(author.getId());
        return authorUpdateDto;

    }

    public AuthorDto getOne(Long id) throws NotFoundException {

        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new NotFoundException("User dosen't exist : " + id);
        }

        AuthorDto authorOneDto = modelMapper.map(author.get(), AuthorDto.class);
        authorOneDto.setId(id);
        authorOneDto.getBooks().forEach(data -> {
            data.setAuthorId(id);
        });
        return authorOneDto;

    }

    public Boolean delete(Long id) throws NotFoundException {

        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new NotFoundException("User dosen't exist : " + id);
        }
        authorRepository.deleteById(id);
        return true;

    }

    @Override
    public Long count() {
        return authorRepository.count();
    }
}
