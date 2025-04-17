/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 *
 * @author LAPTOP
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement // bật giao tác ở đây
@ComponentScan(basePackages = {
    "com.trantheanh1301.controllers",
    "com.trantheanh1301.service",
    "com.trantheanh1301.repository"

}) 
public class SpringSecurityConfig {  // phiên bảng này không cần kế thừa thằng kia nữa
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    // băm mặt khẩu
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
        
    //Phiên bản mới cần hạt đậu này -> để so sánh các đường dẫn trong cấu hinbhf hpanaf quyền có khópư không 
    //nếu không có nó chỉ so sánh gần đúng
    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector(){
        return new HandlerMappingIntrospector();
    } 
    
    
//    //cấu hình form login và đăng xuất , cấu hình chứng thực và phân quyền
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{ //fix
//        //có thể chỉ định theo đường dẫn trang web hay có thể chỉ dẫn theo phương thức nòa được và trạng thái 
//        // permitAll để cho vào hết những đường dẫn web như là / và /home
//        // chỉ có ai có role admin với được vào api get 
//        //Cho role user vào admin có quyền dùng các api còn lại
//        //chỉ định trang nào là login để thằng spring security làm chứng thực , khi thành công về / là trang chủ thất bại ở /login và có erorr
//        // Nhớ là mình đang chặn security -> nghỉ sao không phải cứ tạo mới một templates là vào đây khai báo thêm  ? 
////        http.csrf(c -> c.disable()).authorizeHttpRequests(requests -> requests.requestMatchers("/", "/home","/products").permitAll().requestMatchers(HttpMethod.GET ,"/api/products" ).hasRole("ADMIN").requestMatchers("/api/**").hasAnyRole("USER","ADMIN")).formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error"));
//            http
//        .csrf(AbstractHttpConfigurer::disable)
//        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Cho phép tất cả request
//        .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error"))
//        .logout(LogoutConfigurer::permitAll);
//        return http.build();
//        
//    }
//    //Phiên bản mới cần hạt đậu này -> để so sánh các đường dẫn trong cấu hinbhf hpanaf quyền có khópư không 
//    //nếu không có nó chỉ so sánh gần đúng
    
    
    //authenticated là phải chứng thực còn permitAll() là cho phép hết
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            Exception {
        http.csrf(c -> c.disable()).authorizeHttpRequests(requests
                -> requests.requestMatchers("/", "/home").authenticated()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/products").permitAll() //để vào được trang add
                        .requestMatchers("/api/users").permitAll()
                        .requestMatchers("/add").permitAll()
                        .requestMatchers("/api/**").permitAll())
                .formLogin(form -> form.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true").permitAll())  // nếu đăng nhập thất bại ra url này
                .logout(logout
                        -> logout.logoutSuccessUrl("/login").permitAll());
        return http.build();
    }

  

    //
//    Tạo hạt đậu cho cloudinary -> mình lấy cloud của git
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
}
