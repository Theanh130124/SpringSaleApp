/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.trantheanh1301.pojo.User;
import com.trantheanh1301.repository.UserRepository;
import com.trantheanh1301.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LAPTOP
 */
@Service("userDetailService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo; 
    
    @Autowired
    private Cloudinary cloudinary ;
     @Autowired
    private BCryptPasswordEncoder passswordEncoder;

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
        authories.add(new SimpleGrantedAuthority(u.getUserRole())); // có thể kiểm tra nhiều trường
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword() , authories);
    }

    @Override
    public User register(Map<String,String> params , MultipartFile avatar) {
        User u = new User();
        //set xung csdl tu get cua parmars (theo value)
           u.setFirstName(params.get("firstName"));
        u.setLastName(params.get("lastName"));
        u.setUsername(params.get("username"));
        u.setEmail(params.get("email"));
        u.setPhone(params.get("phone"));
        u.setPassword(this.passswordEncoder.encode(params.get("password")));
        u.setUserRole("ROLE_USER");
        if (!avatar.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return this.userRepo.register(u);
       
     
    }
    }
    

