/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import com.trantheanh1301.pojo.Product;
import com.trantheanh1301.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LAPTOP
 */
@RestController
@RequestMapping("/api")
//các api bên dưới sẽ có cùng tên  /api 
public class ApiProductController {
    
    
    @Autowired
    private ProductService proService ; 
    
    @GetMapping("/products")
    public ResponseEntity<List<Product>> list (){
        List<Product> prods = this.proService.getProducts(null);
        return new  ResponseEntity<>(prods,HttpStatus.OK); 
        //Phân trang api 
    }
    
}
