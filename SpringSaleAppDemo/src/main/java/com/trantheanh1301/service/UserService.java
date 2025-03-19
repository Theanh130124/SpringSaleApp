/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.trantheanh1301.service;

import com.trantheanh1301.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author LAPTOP
 */
public interface UserService extends UserDetailsService{
    User getUserByUsername(String username);
}
