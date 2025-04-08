/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import com.trantheanh1301.pojo.User;
import com.trantheanh1301.service.UserService;
import java.util.Map;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author LAPTOP
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {
    
    @Autowired
    private UserService  userDetailService;
    
    
    //gửi trong body 
    @PostMapping(path = "/users" , consumes =  MediaType.MULTIPART_FORM_DATA)
    public ResponseEntity<User> register(@RequestParam Map<String,String> params ,@RequestParam(value="avatar") MultipartFile avatar){
        // gán xuống cho repo xử lý params và avatar từ FE
    
        //trả ra dữ liệu post với status code
        return new ResponseEntity<>(this.userDetailService.register(params, avatar) , HttpStatus.CREATED);
    }
}
