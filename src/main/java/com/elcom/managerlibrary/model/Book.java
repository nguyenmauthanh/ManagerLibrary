package com.elcom.managerlibrary.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BOOK")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "BOOK_NAME")
    String bookName;

    @Column(name = "DESCRIPTION")
    String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID")
    Author author;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category category;


}
