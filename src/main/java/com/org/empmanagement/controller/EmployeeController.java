package com.org.empmanagement.controller;

import com.org.empmanagement.dto.EmployeeDto;
import com.org.empmanagement.exception.EmailAreadyInUseException;
import com.org.empmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto empDto)
    {
        try {
            if (this.employeeService.isEmailUnique(empDto.getEmail())) {
                EmployeeDto createdEmployee = employeeService.createEmployee(empDto);
                return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
            } else
                throw new EmailAreadyInUseException("Email already in use");
        }
        catch(EmailAreadyInUseException e){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false) String order)
    {
            return new ResponseEntity<>(this.employeeService.allEmployeesList(),HttpStatus.OK);

    }

    @GetMapping(path="/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(value="id") int id)
    {
        return new ResponseEntity<>(this.employeeService.employeeById(id),HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value="id") int id,@RequestBody EmployeeDto dto)
    {
           return new ResponseEntity<>(this.employeeService.updateEmployee(id,dto),HttpStatus.OK);
    }



}
