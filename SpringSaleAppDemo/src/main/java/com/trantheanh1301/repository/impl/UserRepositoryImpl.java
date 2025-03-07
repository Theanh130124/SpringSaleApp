/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.repository.impl;

import com.trantheanh1301.pojo.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author admin
 */
public class UserRepositoryImpl {

    @Autowired
    private LocalSessionFactoryBean factory;

    public User getUserByUsername(String username) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername"); // tên query name
        q.setParameter("username", username);// "username" -> là tên của truy vấn ở from trong query -> username là biến truyền vào

        return (User) q.getSingleResult();

    }
}
