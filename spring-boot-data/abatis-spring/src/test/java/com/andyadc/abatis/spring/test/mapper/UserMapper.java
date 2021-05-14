package com.andyadc.abatis.spring.test.mapper;

import com.andyadc.abatis.spring.test.User;

public interface UserMapper {

    User selectByName(String name);
}
