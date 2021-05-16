package com.andyadc.abatis.spring.boot.test.mapper;

import com.andyadc.abatis.spring.boot.test.User;

public interface UserMapper {

    User selectByName(String name);
}
