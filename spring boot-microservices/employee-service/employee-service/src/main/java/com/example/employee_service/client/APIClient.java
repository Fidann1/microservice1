package com.example.employee_service.client;


import com.example.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="http://localhost:8080", value="ms-department")
public interface APIClient {
    @GetMapping("/api/department/{departmentCode}")
     DepartmentDto getDepartment(@PathVariable String departmentCode);

}
