package com.elcom.managerlibrary.repository;

import com.elcom.managerlibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select a from User a where a.userName = :username")
    User findByUserName(String username);
}
