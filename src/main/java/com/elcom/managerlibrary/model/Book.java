package com.elcom.managerlibrary.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "BOOK")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    Long id;

    @Column(name = "bookName" , unique = true)
    String bookName;

    @Column(name = "description")
    String description;

    @Column(name = "bookStatus", length = 50)
    @Enumerated(EnumType.STRING)
    BookStatus bookStatus;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToOne(cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;


}
