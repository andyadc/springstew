package com.andyadc.data.test;

import com.andyadc.data.entity.Company;
import com.andyadc.data.entity.Employee;
import com.andyadc.data.repository.CompanyRepository;
import com.andyadc.data.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class RepositoryTests {

    @Resource
    private EmployeeRepository employeeRepository;
    @Resource
    private CompanyRepository companyRepository;

    @Test
    public void testEmployeeRepository() {
        employeeRepository.count();
    }

    @Test
    public void testInitData() {
        if (companyRepository.count() == 0) {
            companyRepository.saveAll(
                    Stream.of("Path-Way Electronics", "E-Tech Management", "Path-E-Tech Management")
                            .map(Company::new)
                            .collect(Collectors.toList())
            );
        }

        if (employeeRepository.count() == 0) {
            List<Company> companies = companyRepository.findAll();
            Random random = new SecureRandom();
            employeeRepository.saveAll(
                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                            "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                            "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                            "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                            "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                            "Jaydan Jackson", "Bernard Nilsen")
                            .map(name -> {
                                Employee employee = new Employee();
                                employee.setName(name);
                                employee.setCompany(companies.get(random.nextInt(companies.size())));
                                return employee;
                            }).collect(Collectors.toList())
            );
        }

    }
}
