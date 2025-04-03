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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LAPTOP
 */
@RestController
@RequestMapping("/api")
public class ApiProductConttroller {
    
    @Autowired
    private ProductService proService ;
    
    
    public ResponseEntity<List<Product>> listPro(){
        List<Product> prods = this.proService.getProducts(null);
        return new ResponseEntity<>(prods,HttpStatus.OK);
    }
    @DeleteMapping("products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deytroy(@PathVariable(value= "productId") int id){
        this.proService.deleteProduct(id);
    }
    
}
