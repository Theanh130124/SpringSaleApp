/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author LAPTOP
 */
public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    // 
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }
    //Khai báo gỗ đậu ở trong này
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
            WebAppContextConfigs.class
        } ;
    }
    //Kí hiệu đường dẫn trên web -> thường thì dùng dấu / 
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; 
    }
    
}
