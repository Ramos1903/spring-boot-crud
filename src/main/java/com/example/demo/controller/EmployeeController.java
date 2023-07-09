package com.example.demo.controller;


import com.example.demo.dto.EmployeeDTO;
import com.example.demo.mapstruct.EmployeeMapStruct;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long employeeId) {
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") long employeeId,
                                                      @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.getEmployeeById(employeeId)
                .map(savedEmployee -> {

                    savedEmployee.setFirstName(employeeDTO.getFirstName());
                    savedEmployee.setLastName(employeeDTO.getLastName());
                    savedEmployee.setEmail(employeeDTO.getEmail());

                    EmployeeDTO updatedEmployee = employeeService.updateEmployee(EmployeeMapStruct.INSTANCE.dtoToEntity(savedEmployee));
                    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId) {

        employeeService.deleteEmployee(employeeId);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);

    }
}