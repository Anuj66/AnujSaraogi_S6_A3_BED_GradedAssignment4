package com.greatlearning.employeeManagementSystem.repository;

import com.greatlearning.employeeManagementSystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<Employee> findByFirstName(String firstName);

}
