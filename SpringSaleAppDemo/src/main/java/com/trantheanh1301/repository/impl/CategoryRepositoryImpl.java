/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.repository.impl;


import com.trantheanh1301.pojo.Category;
import com.trantheanh1301.repository.CategoryRepository;
import java.util.List;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
   
    
    @Override
    public List<Category> getCates()  {
            Session s  = factory.getObject().getCurrentSession();
            Query q = s.createQuery("From Category");
            return q.getResultList();
        
    }
}