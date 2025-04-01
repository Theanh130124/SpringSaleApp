/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.trantheanh1301.formatters.CategoryFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
    "com.trantheanh1301.service",
    "com.trantheanh1301.repository"

})  //Chỉ định nơi tạo hạt đậu -> cứ dùng annotation liên quan mvc thì đều khai báo vào đây
public class WebAppContextConfigs implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();// giúp xử lý các yêu cầu tài nguyên tĩnh (như CSS, JavaScript, hình ảnh) mà không bị Spring MVC chặn.
    }

    //Những thằng bỏ vào đây có thể public -> để add static và ảnh
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        //Cái location là đường dẫn vật lý (đuonbgừ dẫn thật còn trước đó là đường dẫn giả show ra ngoài inpect
    }

    //Xu ly formatter khi gửi id -> thì nó tự đống gói thành đối tượng
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CategoryFormatter());
    }

    //Tạo hạt đậu cho cloudinary -> mình lấy cloud của git
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dxiawzgnz",
                        "api_key", "916324835836949",
                        "api_secret", "it9HP_2TUJjIHLSshkbm0BYA5qE",
                        "secure", true));
        return cloudinary;
    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver
//                = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        return resolver;
//    }

}
