package com.andyadc.data.mapper;

import com.andyadc.data.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * https://mybatis.org/mybatis-3/zh/java-api.html
 */
@Mapper
public interface EmployeeMapper {

    @Results(value = {
            @Result(property = "name", column = "name")
    })
    @Select(value = "select * from employee where id = #{id}")
    Employee findById(Long id);

    @Insert("INSERT INTO employee (name) VALUES (#{name})")
    @SelectKey(statement = "SELECT last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
    int insert(Employee employee);

    @Update("UPDATE employee SET name = #{name} WHERE id = #{id}")
    Integer updateById(Employee employee);

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetEmployeesByName")
    List<Employee> selectByName(String name);

    String selectNow();

    // implements ProviderMethodResolver
    class UserSqlBuilder {
        public static String buildGetEmployeesByName(final String name) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("employee");
                    if (name != null) {
                        WHERE("name like #{value} || '%'");
                    }
                    ORDER_BY("id");
                }
            }.toString();
        }
    }
}
