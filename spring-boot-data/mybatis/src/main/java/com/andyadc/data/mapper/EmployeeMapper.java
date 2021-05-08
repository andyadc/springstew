package com.andyadc.data.mapper;

import com.andyadc.data.entity.Employee;
import org.apache.ibatis.annotations.DeleteProvider;
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

    @SelectProvider(type = UserSqlProvider.class, method = "buildGetEmployeesByName")
    List<Employee> selectByName(String name);

    @SelectProvider(type = UserSqlProvider.class, method = "buildGetEmployeesByExample")
    List<Employee> selectByExample(Employee example);

    @DeleteProvider(type = UserSqlProvider.class, method = "buildDeleteByExample")
    int delByExample(Employee example);

    String selectNow();

    // implements ProviderMethodResolver
    class UserSqlProvider {
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

        public static String buildGetEmployeesByExample(final Employee example) {
            SQL sql = new SQL();
            sql.SELECT("*").FROM("employee");
            if (example.getName() != null && example.getName().trim().length() > 0) {
                sql.AND().WHERE("name like concat('%', #{name}, '%')");
            }
            // ...
            sql.ORDER_BY("id desc", "name asc");
            return sql.toString();
        }

        public static String buildUpdateEmployeeByExample(final Employee example) {
            SQL sql = new SQL();
            sql.UPDATE("employee");
            if (example.getName() != null && example.getName().trim().length() > 0) {
                sql.SET("name = #{name}");
            }
            sql.WHERE("id = #{id}");
            return sql.toString();
        }

        public static String buildSaveEmployee(final Employee example) {
            SQL sql = new SQL();
            sql.INSERT_INTO("employee");
            sql.INTO_COLUMNS("name")
                    .INTO_VALUES("#{name}");
            return sql.toString();
        }

        public static String buildDeleteByExample(final Employee example) {
            SQL sql = new SQL();
            sql.DELETE_FROM("employee");
            sql.WHERE("name=#{name}", "id=#{id}");
            return sql.toString();
        }
    }
}
