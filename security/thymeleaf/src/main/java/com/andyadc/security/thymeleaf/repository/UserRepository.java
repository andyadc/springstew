package com.andyadc.security.thymeleaf.repository;

import com.andyadc.security.thymeleaf.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select * from user where username = :username")
    User findByUsername(String username);
}
