package com.andyadc.data.test;

import com.andyadc.data.entity.Employee;
import com.andyadc.data.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MapperTests {

    @Resource
    public EmployeeMapper employeeMapper;

    @Test
    public void testEmployeeMapper() {
        System.out.println(employeeMapper.findById(1L));
        System.out.println(employeeMapper.selectNow());
        Employee employee = new Employee();
        employee.setName("adc");
        employeeMapper.insert(employee);
    }
}
