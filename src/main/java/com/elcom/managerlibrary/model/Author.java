package com.elcom.managerlibrary.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "authorName")
    String authorName;

    @Column(name = "description")
    String description;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = CascadeType.ALL)
    List<Book> booksList;

}
