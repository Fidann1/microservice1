package com.example.employee_service.controller;

import com.example.employee_service.dto.ApiDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.service.inter.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("{email}")
    private ResponseEntity<EmployeeDto> getEmployeeDtoByEmail(@PathVariable String email) {
        return new ResponseEntity<>(employeeService.getEmployeeByEmail(email),HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto=employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<ApiDto> getEmployeeDtoById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }
}
