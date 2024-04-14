package com.org.empmanagement.service;

import com.org.empmanagement.dto.EmployeeDto;

import java.util.List;


public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto dto);
    List<EmployeeDto> allEmployeesList();

    EmployeeDto employeeById(int id);
    EmployeeDto updateEmployee(int id, EmployeeDto dto);

    boolean isEmailUnique(String email);

}
