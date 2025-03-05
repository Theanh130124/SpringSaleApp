/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.trantheanh1301.repository;

import com.trantheanh1301.pojo.Product;
import java.util.List;
import java.util.Map;

public interface ProductRepository {
    public List<Product> getProducts(Map<String, String> params);
    public void addOrUpdate(Product p);
    public Product getProductById(int id);
}
