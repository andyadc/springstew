package com.andyadc.bms.auth.mapper;

import com.andyadc.bms.auth.entity.AuthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthUserMapper {

    @Results(value = {
            @Result(property = "username", column = "username")
    })
    @Select(value = "select * from auth_user where username = #{username} limit 1")
    AuthUser findByUsername(String username);
}
