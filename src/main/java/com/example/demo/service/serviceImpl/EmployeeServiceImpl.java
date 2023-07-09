package com.example.demo.service.serviceImpl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.mapstruct.EmployeeMapStruct;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employeeDTO.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email:" + employeeDTO.getEmail());
        }
        Employee employee = employeeRepository.save(EmployeeMapStruct.INSTANCE.dtoToEntity(employeeDTO));
        return EmployeeMapStruct.INSTANCE.enitityToDto(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> {
            employeeDTOS.add(EmployeeMapStruct.INSTANCE.enitityToDto(employee));
        });
        return employeeDTOS;
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(long id) {
       Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(value -> Optional.ofNullable(EmployeeMapStruct.INSTANCE.enitityToDto(value))).orElse(null);
    }

    @Override
    public EmployeeDTO updateEmployee(Employee updatedEmployee) {
        Employee emp = employeeRepository.save(updatedEmployee);
        return EmployeeMapStruct.INSTANCE.enitityToDto(emp);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
