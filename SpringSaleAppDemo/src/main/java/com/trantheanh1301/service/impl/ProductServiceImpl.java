/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.service.impl;

import com.trantheanh1301.pojo.Product;
import com.trantheanh1301.repository.ProductRepository;
import com.trantheanh1301.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LAPTOP
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository proRepo;
    
    
    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.proRepo.getProducts(params);
    }

    @Override
    public Product addOrUpdate(Product p) {
         return this.proRepo.addOrUpdate(p);
    }

    @Override
    public Product getProductById(int id) {
       return  this.proRepo.getProductById(id);
    }
    
}
