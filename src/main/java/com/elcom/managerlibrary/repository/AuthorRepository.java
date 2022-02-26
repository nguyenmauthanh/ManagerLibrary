package com.elcom.managerlibrary.repository;

import com.elcom.managerlibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmail(String email);

    @Query("select a from Author a where a.authorName = :authorName")
    List<Author> findByAuthorName(String authorName);
}
