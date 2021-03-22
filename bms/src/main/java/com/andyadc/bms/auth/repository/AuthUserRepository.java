package com.andyadc.bms.auth.repository;

import com.andyadc.bms.auth.entity.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);
}
