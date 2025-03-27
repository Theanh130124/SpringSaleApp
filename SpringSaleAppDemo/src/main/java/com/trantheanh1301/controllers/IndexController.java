/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.controllers;

import com.trantheanh1301.pojo.Product;
import com.trantheanh1301.repository.CategoryRepository;
import com.trantheanh1301.service.CategoryService;
import com.trantheanh1301.service.ProductService;
import java.util.Map;
import org.hibernate.query.Query; // Lưu ý import này
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LAPTOP
 */
@Controller
@PropertySource("classpath:configs.properties")

public class IndexController {

    @Autowired // phải có này để Spring quản lý
    private ProductService productService;
    @Autowired
    private CategoryService cateService;
    @Autowired
    private Environment env;
    
    
    //Những thứ mà dùng chung -> luôn trả ra thằng này
    @ModelAttribute
    public void commonAttr(Model model){
        
        model.addAttribute("categories", this.cateService.getCates()); // để danh mục trên header luôn có ở mọi controller -> hay templates
        
    }

    //Muốn gửi dữ liệu đi thì phải có model
//    @RequestMapping("/")
//    public String index(Model model){
//        
//        model.addAttribute("message", "Thế Anh");
//        return "index";
//        //Trả về tên view
//    }
    @RequestMapping("/")
    @Transactional
    //truyền params vào -> nhưng cateId trên đường dẫn phải đúng tên với trong getProducts
    public String index(Model model ,  @RequestParam Map<String,String> params) {

        
        model.addAttribute("products", this.productService.getProducts(params)); // null la lay het -> truyền params để hứng cateId trên web
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.productService.countProduct();
        //nhận 1.0 để ra double để làm tròn lên số trang 
        model.addAttribute("counter",Math.ceil(count*1.0/pageSize)); // Tổng trang hiện có 
        
        int currentPage = params.containsKey("page") ? Integer.parseInt(params.get("page")) : 1; 
        model.addAttribute("currentPage", currentPage); // để có hiện trang trước hay không ( ở trang 1 thì không hiện trang trước)
        return "index";
    }
    
    // login này đã giao cho Spring Security làm nên mình chỉ cung  cấp view
//    @RequestMapping("/login")
//    public String loginView(Model model){
//        return "login" ; 
//    }


}
