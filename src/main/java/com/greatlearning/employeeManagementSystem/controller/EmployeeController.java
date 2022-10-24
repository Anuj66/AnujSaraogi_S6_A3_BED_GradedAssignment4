package com.greatlearning.employeeManagementSystem.controller;

import com.greatlearning.employeeManagementSystem.models.Employee;
import com.greatlearning.employeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> listAllEmployees() {
        return new ResponseEntity<>(employeeService.listAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Employee> listEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Employee> updateById(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateById(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/search/{firstName}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> listByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(employeeService.listByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/sort")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> listAllSortedByFirstName(
            @RequestParam(name = "order", required = true) String order) {
        return new ResponseEntity<>(employeeService.listAllEmployeeSortedByFirstName(order), HttpStatus.OK);
    }

}
