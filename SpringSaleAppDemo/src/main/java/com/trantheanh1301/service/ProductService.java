/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.trantheanh1301.service;

import com.trantheanh1301.pojo.Product;

import java.util.List;
import java.util.Map;


/**
 *
 * @author LAPTOP
 */
public interface ProductService {
    


    List<Product> getProducts(Map<String, String> params);

    Product addOrUpdate(Product p);

    Product getProductById(int id);
}
