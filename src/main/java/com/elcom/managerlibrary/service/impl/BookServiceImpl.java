package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.dto.AuthorDtoForOneEntity;
import com.elcom.managerlibrary.dto.BookDto;
import com.elcom.managerlibrary.dto.BookUpdateDto;
import com.elcom.managerlibrary.exception.NotFoundException;
import com.elcom.managerlibrary.model.Author;
import com.elcom.managerlibrary.model.Book;
import com.elcom.managerlibrary.repository.AuthorRepository;
import com.elcom.managerlibrary.repository.BookRepository;
import com.elcom.managerlibrary.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(ModelMapper modelMapper, BookRepository bookRepository, AuthorRepository authorRepository){
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDto save(BookDto bookDto) throws Exception {
        List<Book> bookChecked = bookRepository.SearchBooksByName(bookDto.getBookName().trim());
        if (bookChecked.size() > 0) {
            throw new Exception("book already exist");
        }

        Optional<Author> authorOpt = authorRepository.findById(bookDto.getAuthorId());
        if (!authorOpt.isPresent() && authorOpt.get().getId() != bookDto.getAuthorId()) {
            throw new IllegalArgumentException("Author does not match");
        }

        Book book = modelMapper.map(bookDto, Book.class);
        book.setAuthor(authorOpt.get());
        bookRepository.save(book);

        bookDto.setId(book.getId());

        bookDto.setAuthor(modelMapper.map(authorOpt.get(), AuthorDtoForOneEntity.class));
        return bookDto;
    }

    @Override
    public List<BookDto> getAll() throws NotFoundException {
        List<Book> books = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        if (books.size() < 1) {
            throw new NotFoundException("Book don't already exist");
        }
        BookDto[] bookDtos = modelMapper.map(books, BookDto[].class);
        Arrays.asList(bookDtos).forEach(data -> {
            data.setAuthorId(data.getAuthor().getId());
        });
        return Arrays.asList(bookDtos);
    }

    @Transactional
    @Override
    public BookUpdateDto update(Long id, BookUpdateDto bookUpdateDto) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book does not exist id : " + id);
        }
        Optional<Author> author = authorRepository.findById(bookUpdateDto.getAuthorId());
        if (!author.isPresent()) {
            throw new NotFoundException("Author does not exist id : " + bookUpdateDto.getAuthorId());
        }
        Book realbook = modelMapper.map(bookUpdateDto, Book.class);
        realbook.setAuthor(author.get());
        realbook.setCategory(bookOpt.get().getCategory());
        bookRepository.save(realbook);
        bookUpdateDto = modelMapper.map(realbook, BookUpdateDto.class);
        bookUpdateDto.setAuthorId(author.get().getId());

        return bookUpdateDto;
    }

    @Override
    public BookDto getOne(Long id) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new NotFoundException("Book does not exist id : " + id);
        }
        BookDto bookOneDto = modelMapper.map(book.get(), BookDto.class);
        bookOneDto.setId(id);
        bookOneDto.setAuthorId(bookOneDto.getAuthor().getId());

        return bookOneDto;
    }

    @Override
    public Boolean delete(Long id) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new NotFoundException("Book does not exist id : " + id);
        }
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public List<BookDto> SearchBooksByName(String name) throws NotFoundException {
        List<Book> books = bookRepository.SearchBooksByName(name.trim());
        if (books.size() < 1) {
            throw new NotFoundException("Book don't already exist");
        }
        BookDto[] bookDtos = modelMapper.map(books, BookDto[].class);
        return Arrays.asList(bookDtos);
    }
}
