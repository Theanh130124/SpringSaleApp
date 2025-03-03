/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LAPTOP
 */
@Controller
public class IndexController {
    private LocalSessionFactoryBean factory ; 
    
    
    //Muốn gửi dữ liệu đi thì phải có model
//    @RequestMapping("/")
//    public String index(Model model){
//        
//        model.addAttribute("message", "Thế Anh");
//        return "index";
//        //Trả về tên view
//    }
    @RequestMapping("/")
    @Transactional
    public String index(Model model){
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Product");
        model.addAttribute("products",q.getResultList());
        return "index";
    }
    
}
