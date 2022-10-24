package com.greatlearning.employeeManagementSystem.service.serviceImpl;

import com.greatlearning.employeeManagementSystem.models.Employee;
import com.greatlearning.employeeManagementSystem.repository.EmployeeRepository;
import com.greatlearning.employeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public String addEmployee(Employee employee) {
        if(employee.getEmail() == null) {
            throw new RuntimeException("Error : Please provide an email address");
        }
        if(employee.getFirstName() == null) {
            throw new RuntimeException("Error : Please provide an First Name");
        }
        if(employeeRepository.existsByEmail(employee.getEmail())) {
           throw new RuntimeException("Error : Employee with this email already exists");
        }
        employeeRepository.save(employee);
        return "Employee Saved Successfully";
    }

    @Override
    public List<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Error : Employee Not Found");
                });
    }

    @Override
    public Employee updateById(Long id, Employee employee) {
        Employee employeeExists = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Error : Employee Not Found");
                });
        if(employee.getFirstName() != null) {
            employeeExists.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null) {
            employeeExists.setLastName(employee.getLastName());
        }
        if(employee.getEmail() != null) {
            employeeExists.setEmail(employee.getEmail());
        }
        employeeExists.setId(id);
        employeeRepository.save(employeeExists);
        return employeeExists;
    }

    @Override
    public String deleteById(Long id) {
        if(!employeeRepository.existsById(id)) {
            throw new RuntimeException("Error : Employee Not Found");
        }
        employeeRepository.deleteById(id);
        return "Employee Deleted Successfully";
    }

    @Override
    public List<Employee> listByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    public List<Employee> listAllEmployeeSortedByFirstName(String order) {
        List<Employee> employees = new ArrayList<>();
        if(order.equals("asc") || order.equals("ASC")) {
            employees = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        }
        if(order.equals("desc") || order.equals("DESC")) {
            employees = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
        }
        return employees;
    }

}
