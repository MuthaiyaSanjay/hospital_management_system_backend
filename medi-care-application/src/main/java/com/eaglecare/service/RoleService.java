package com.eaglecare.service;

import com.eaglecare.entity.RoleEntity;
import com.eaglecare.exception.ResourceNotFoundException;
import com.eaglecare.model.Role;
import com.eaglecare.repository.RoleRepo;
import com.sun.jdi.request.DuplicateRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleRepo roleRepo;


    public Role saveRole(Role role) {
        Optional<RoleEntity> existingRole = roleRepo.findByName(role.getName());
        if (existingRole.isPresent()) {
            throw new DuplicateRequestException("Role Id is Already Exists");
        }
        RoleEntity roleEntity = modelMapper.map(role, RoleEntity.class);
        RoleEntity savedRole = roleRepo.save(roleEntity);
        return modelMapper.map(savedRole, Role.class);
    }

    public Role updateRole(Long id, Role roleDetails) {
        RoleEntity roleEntity = roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        Optional<RoleEntity> existingRole = roleRepo.findByName(roleDetails.getName());
        if (existingRole.isPresent()) {
            throw new DuplicateRequestException("Role Id is Already Exists");
        }
        roleEntity.setName(roleDetails.getName());
        roleEntity.setDescription(roleDetails.getDescription());
        RoleEntity updatedRoleEntity = roleRepo.save(roleEntity);
        return modelMapper.map(updatedRoleEntity, Role.class);
    }

    public boolean deleteRole(Long id) {
        RoleEntity roleEntity = roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        roleRepo.delete(roleEntity);
        return true;
    }

    public List<Role> getAllRoles(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<RoleEntity> rolePage = roleRepo.findAll(pageable);
        return rolePage.getContent().stream()
                .map(this::convertToRole)
                .collect(Collectors.toList());
    }

    private Role convertToRole(RoleEntity roleEntity) {
        Role role = new Role();
        role.setId(roleEntity.getId().toString());
        role.setDescription(roleEntity.getDescription());
        role.setName(roleEntity.getName());
        return role;
    }


    public Role getRoleById(Long id) {
        RoleEntity roleEntity = roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        return modelMapper.map(roleEntity, Role.class);
    }


}
