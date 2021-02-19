package com.andyadc.data.test;

import com.andyadc.data.respository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class RepositoryTests {

    @Resource
    private EmployeeRepository employeeRepository;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testEmployeeRepository() {
//        System.out.println(employeeRepository.findByName("Eduardo Haugen"));
        System.out.println(employeeRepository.deleteByCompanyId(1L));
    }

    @Test
    public void testJdbcTemplate() {
        String name = "Koen Johansen";
        jdbcTemplate.execute("delete from employee where name = '" + name + "'");
    }
}
