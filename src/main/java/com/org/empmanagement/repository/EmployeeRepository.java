package com.org.empmanagement.repository;

import com.org.empmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
    @Query("SELECT u FROM Employee u WHERE u.email= :email")
    public Employee getEmployeeByEmail(@Param("email") String email);
}
