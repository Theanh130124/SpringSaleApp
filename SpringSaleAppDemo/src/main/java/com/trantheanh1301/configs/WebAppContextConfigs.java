/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Quy định các quy tác 1 web mvc cần tuân thủ
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author LAPTOP
 */
@Configuration //đánh dấu một class là cấu hình Spring
@EnableWebMvc // không cần ghi đè hết WebMvcConfigurer 
@EnableTransactionManagement // bật giao tác ở đây
@ComponentScan(basePackages = {
    "com.trantheanh1301.controllers",
        "com.trantheanh1301.configs"

})  //Chỉ định nơi tạo hạt đậu -> cứ dùng annotation liên quan mvc thì đều khai báo vào đây
public class WebAppContextConfigs implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();// giúp xử lý các yêu cầu tài nguyên tĩnh (như CSS, JavaScript, hình ảnh) mà không bị Spring MVC chặn.
    }

}
