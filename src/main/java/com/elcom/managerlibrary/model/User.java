package com.elcom.managerlibrary.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "USER"
    , indexes = {
        @Index(name = "idx_username", columnList = "userName")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "userName")
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "fullName")
    String fullName;

    @Column(name = "roleName")
    String roleName;

}
