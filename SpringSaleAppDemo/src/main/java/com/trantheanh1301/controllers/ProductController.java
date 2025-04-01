/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import com.trantheanh1301.pojo.Product;
import com.trantheanh1301.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * Các phương thức controller xử lý trên templetes
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService proService;
    
    //Load danh sách sản phẩm lên
    @GetMapping("products")
    public String createView(Model model){
        model.addAttribute("product", new Product());//tên này trùng với tên trong form
        return "products" ;
    }
    
    //link với action add bên thymeleaf
    @PostMapping("/add")
    //value nhân từ form
    public String add(@ModelAttribute(value="product") Product p){
        this.proService.addOrUpdate(p);
        return "redirect:/";
        //Sau khi thêm sản phẩm thành công, trang web sẽ chuyển hướng (redirect) về trang chủ /
    }
    //Cập nhật sản phẩm
    @GetMapping("products/{productId}")
    public String updateView(Model model , @PathVariable(value= "productId") int id)
    {
        model.addAttribute("product", this.proService.getProductById(id));
        return "products";
    }
}
