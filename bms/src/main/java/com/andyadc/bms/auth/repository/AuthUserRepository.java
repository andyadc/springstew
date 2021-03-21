package com.andyadc.bms.auth.repository;

import com.andyadc.bms.auth.entity.AuthUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);
}
