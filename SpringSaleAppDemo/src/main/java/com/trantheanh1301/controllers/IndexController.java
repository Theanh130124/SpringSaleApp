/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LAPTOP
 */
@Controller
public class IndexController {
    //Muốn gửi dữ liệu đi thì phải có model
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("message", "Xin Chào");
        return "index";
        //Trả về tên view
    }
    
}
