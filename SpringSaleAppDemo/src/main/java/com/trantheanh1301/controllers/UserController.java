/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author LAPTOP
 */
@Controller
public class UserController {
    
    
    // thằng Spring security làm sẳn đăng nhập va đăng xuất cho mình rồi
    @GetMapping("/login")
    public String loginView(){
        return "login";
    }
    
}
