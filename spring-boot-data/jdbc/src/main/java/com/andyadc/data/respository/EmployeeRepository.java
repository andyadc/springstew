package com.andyadc.data.respository;

import com.andyadc.data.entity.Employee;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select * from employee where name = :name")
    Employee findByName(@Param("name") String name);

    @Modifying
    @Query("delete from employee where company_id = :companyId")
    int deleteByCompanyId(@Param("companyId") Long companyId);
}
