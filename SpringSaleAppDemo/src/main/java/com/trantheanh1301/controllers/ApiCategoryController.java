/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import com.trantheanh1301.pojo.Category;
import com.trantheanh1301.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LAPTOP
 */


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiCategoryController {
     @Autowired
    private CategoryService cateService;
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> list() {
        return new ResponseEntity<>(this.cateService.getCates(), HttpStatus.OK);
    }
    
}
