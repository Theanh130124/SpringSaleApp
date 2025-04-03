/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.configs;


import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author LAPTOP
 */
public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    // Cấu hình những thằng dùng chung  -> giúp tầng web truy cập mà k cần hỏi Dispacherserlvet
    //Tang database , security , hibernate
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            HibernateConfig.class, // Khai báo hibernate ở đây
            ThymeleafConfig.class,
           SpringSecurityConfig.class,
        };
    }

    //Khai báo gỗ đậu ở trong này -> Cấu hình những thằng dùng riêng
    //Tang web
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            WebAppContextConfigs.class,};
    }

    //Kí hiệu đường dẫn trên web -> thường thì dùng dấu / 
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    
    
    // upload cloudinary
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String location = "/"; //lưu ở đây trên cloud  /user thì phải tạo folder user trên đó
        long maxFileSize = 5242880; // 5MB
        long maxRequestSize = 20971520; // 20MB
        int fileSizeThreshold = 0;

        registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    }


}
