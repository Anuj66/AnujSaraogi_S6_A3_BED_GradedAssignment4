package com.greatlearning.employeeManagementSystem.service;

import com.greatlearning.employeeManagementSystem.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    String addEmployee(Employee employee);
    List<Employee> listAllEmployees();
    Employee findById(Long id);
    Employee updateById(Long id, Employee employee);
    String deleteById(Long id);
    List<Employee> listByFirstName(String firstName);
    List<Employee> listAllEmployeeSortedByFirstName(String order);

}
