package com.andyadc.data.mapper;

import com.andyadc.data.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    @Results(value = {
            @Result(property = "name", column = "name")
    })
    @Select(value = "select * from employee where id = #{id}")
    Employee findById(Long id);

    @Insert("INSERT INTO employee (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Employee employee);

    String selectNow();
}
