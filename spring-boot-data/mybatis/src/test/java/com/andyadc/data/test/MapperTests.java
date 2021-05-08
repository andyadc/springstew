package com.andyadc.data.test;

import com.andyadc.data.entity.Employee;
import com.andyadc.data.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperTests {

    @Resource
    public EmployeeMapper employeeMapper;

    @Test
    public void testDel() {
        Employee example = new Employee();
        example.setId(1L);
        example.setName("adc");
        employeeMapper.delByExample(example);
    }

    @Test
    public void testEmployeeMapper() {
//        System.out.println(employeeMapper.findById(1L));

//        System.out.println(employeeMapper.selectNow());

//        Employee employee = new Employee();
//        employee.setName("adc");
//        System.out.println(employeeMapper.insert(employee));

//        System.out.println(employee.getId());

//        employeeMapper.selectByName("adc").forEach(System.out::println);
        Employee example = new Employee();
        example.setName("adc");
        employeeMapper.selectByExample(example).forEach(System.out::println);
    }
}
