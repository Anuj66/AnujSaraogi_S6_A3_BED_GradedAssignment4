package com.greatlearning.employeeManagementSystem.controller;

import com.greatlearning.employeeManagementSystem.models.Role;
import com.greatlearning.employeeManagementSystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/addRole")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addRole(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.CREATED);
    }

}
