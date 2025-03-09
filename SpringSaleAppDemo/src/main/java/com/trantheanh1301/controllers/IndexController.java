/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import com.trantheanh1301.repository.CategoryRepository;
import com.trantheanh1301.service.CategoryService;
import com.trantheanh1301.service.ProductService;
import org.hibernate.query.Query; // Lưu ý import này
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired // phải có này để Spring quản lý
    private ProductService productService;
    @Autowired
    private CategoryService cateService;

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
    public String index(Model model) {
        
        model.addAttribute("categories", this.cateService.getCates());
        model.addAttribute("products", this.productService.getProducts(null)); // null la lay het
        return "index";
    }

}
