package com.elcom.managerlibrary.repository;

import com.elcom.managerlibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from User u where u.userName = ?1", nativeQuery = true)
    User findByUserName(String username);
}
