package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    Optional<EmployeeDTO> getEmployeeById(long id);
    EmployeeDTO updateEmployee(Employee updatedEmployee);
    void deleteEmployee(long id);
}
