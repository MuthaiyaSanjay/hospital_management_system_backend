package com.eaglecare.controller;

import com.eaglecare.api.RoleApi;
import com.eaglecare.model.Role;
import com.eaglecare.service.RoleService;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RoleController implements RoleApi {

    @Autowired
    RoleService roleService;

    @Override
    public ResponseEntity<Role> createNewRole(Role role) {
        try {
            Role role1 = roleService.saveRole(role);
            return new ResponseEntity<>(role1, HttpStatus.CREATED);
        } catch (DuplicateRequestException e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> deleteRoleById(String id) {
        try {
            boolean status = roleService.deleteRole(Long.valueOf(id));
            String res = (status) ? "SuccessFully Deleted " : "Deleted have some Issues";
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Role> getRoleById(String id) {
        try {
            Role role1 = roleService.getRoleById(Long.valueOf(id));
            return new ResponseEntity<>(role1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Role>> getRoles(BigDecimal page, BigDecimal count) {
        try {
            BigDecimal bg1 = new BigDecimal(String.valueOf(page));
            BigDecimal bg2 = new BigDecimal(String.valueOf(count));
            int page1 = bg1.intValue();
            int count1 = bg2.intValue();
            List<Role> role1 = roleService.getAllRoles(page1, count1);
            ;
            return new ResponseEntity<>(role1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Role> updateRole(String id, Role role) {
        try {
            Role role1 = roleService.updateRole(Long.valueOf(id), role);
            return new ResponseEntity<>(role1, HttpStatus.OK);
        } catch (DuplicateRequestException e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
