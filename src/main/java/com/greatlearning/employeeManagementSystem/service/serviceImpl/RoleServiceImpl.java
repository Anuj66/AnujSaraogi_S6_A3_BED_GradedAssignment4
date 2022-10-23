package com.greatlearning.employeeManagementSystem.service.serviceImpl;

import com.greatlearning.employeeManagementSystem.models.Role;
import com.greatlearning.employeeManagementSystem.repository.RoleRepository;
import com.greatlearning.employeeManagementSystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public String addRole(Role role) {
        if(roleRepository.existsByName(role.getName())) {
            throw new RuntimeException("Error : Role already exists");
        }
        List<Role> roleList = roleRepository.findAll();
        role.setId(roleList.size() + 1);
        roleRepository.save(role);
        return "Role Saved Successfully";
    }
}
