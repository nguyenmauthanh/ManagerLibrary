package com.elcom.managerlibrary.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "AUTHOR")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "AUTHOR_NAME")
    String authorName;
    @Column(name = "DESCRIPTION")
    String description;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = CascadeType.ALL)
    List<Book> booksList;

}
