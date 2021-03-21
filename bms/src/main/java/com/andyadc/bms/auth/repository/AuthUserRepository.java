package com.andyadc.bms.auth.repository;

import com.andyadc.bms.auth.entity.AuthUser;
import org.springframework.data.repository.CrudRepository;

public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {
}
