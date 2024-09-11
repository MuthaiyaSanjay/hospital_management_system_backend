package com.medicare.controller;

import com.medicare.api.UsersApi;
import com.medicare.entity.UserBasicInfoEntity;
import com.medicare.model.UserPayload;
import com.medicare.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class UserController implements UsersApi {
    @Autowired
    Userservice userservice;

    @Override
    public ResponseEntity<String> changePassword(String id, String oldPassword, String newPassword) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<UserPayload> createNewUser(UserPayload userPayload) {
        try {
            UserPayload user = userservice.save(userPayload);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public  ResponseEntity<String> deleteUserById(String id)  {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<UserPayload> getUserById(String id)  {
        try {
            UserPayload user = userservice.getUserById(Long.parseLong(id));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    //issue for userid is Bigdecimal
    public ResponseEntity<List<UserPayload>> getUsers(BigDecimal page, BigDecimal count, BigDecimal userId, String phoneNumber) {
        try {
            BigDecimal bg1 = new BigDecimal(String.valueOf(page));
            BigDecimal bg2 = new BigDecimal(String.valueOf(count));
            int page1 = bg1.intValue();
            int count1 = bg2.intValue();
            List<UserPayload> user = userservice.getAllUsers(page1,count1);
            return new ResponseEntity<>(user ,HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<UserPayload> updateUsers(String id, UserPayload userPayload) {
        try {
            UserPayload user = userservice.update(id, userPayload);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
