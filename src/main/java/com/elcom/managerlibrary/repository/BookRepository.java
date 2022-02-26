package com.elcom.managerlibrary.repository;

import com.elcom.managerlibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select a from Book a where a.bookName = :bookName")
    List<Book> SearchBooksByName(String bookName);
}
