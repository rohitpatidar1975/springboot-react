package com.org.empmanagement.service.impl;


import com.org.empmanagement.dto.EmployeeDto;
import com.org.empmanagement.entity.Employee;
import com.org.empmanagement.exception.ResourceNotFoundException;
import com.org.empmanagement.repository.EmployeeRepository;
import com.org.empmanagement.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public EmployeeDto createEmployee(EmployeeDto dto){
        Employee emp= this.mapper.map(dto,Employee.class);
        return this.mapper.map(this.employeeRepository.save(emp),EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> allEmployeesList()
    {
        return this.employeeRepository.findAll()
                                      .stream()
                                      .map(employee -> mapper.map(employee,EmployeeDto.class))
                                                             .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto employeeById(int id)
    {
        Employee employee=this.employeeRepository.findById(id).orElse(null);
        if(employee !=null)
            return mapper.map(employee,EmployeeDto.class);
        return null;
    }

    @Override
    public EmployeeDto updateEmployee(int id, EmployeeDto dto)
    {
        //checking if employee exits and if it doesn't then throwing exception else storing result.
        Employee emp= this.employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee not found with id:" +id)
        );
        EmployeeDto updatedEmployeeDto=this.mapper.map(
                this.employeeRepository.saveAndFlush(this.mapper.map(dto,Employee.class)),EmployeeDto.class);

        return null;
    }
    @Override
    public boolean isEmailUnique(String email)
    {
        Employee emp=this.employeeRepository.getEmployeeByEmail(email);
        return emp==null;
    }
}
