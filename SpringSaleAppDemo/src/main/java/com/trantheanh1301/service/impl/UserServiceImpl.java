/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.service.impl;

import com.trantheanh1301.pojo.User;
import com.trantheanh1301.repository.UserRepository;
import com.trantheanh1301.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LAPTOP
 */
@Service("userDetailService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo; 

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    //duoc implement UserDetailService -> de lay user theo username xac thuc
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if(u == null)
        {
            throw new UsernameNotFoundException("Khong tim thay username");
        }
        Set<GrantedAuthority> authories = new HashSet<>() ;
        authories.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword() , authories);
    }
    
}
