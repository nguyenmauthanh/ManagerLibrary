package com.elcom.managerlibrary.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "USERNAME")
    String userName;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "FULL_NAME")
    String fullName;

    @Column(name = "ROLE_NAME")
    String roleName;

}
