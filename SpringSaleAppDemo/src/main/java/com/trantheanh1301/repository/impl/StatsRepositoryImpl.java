///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.trantheanh1301.repository.impl;
//
//import com.trantheanh1301.pojo.OrderDetail;
//import com.trantheanh1301.pojo.Product;
//import com.trantheanh1301.pojo.SaleOrder;
//import java.util.List;
//import jakarta.persistence.Query;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Join;
//import jakarta.persistence.criteria.JoinType;
//import jakarta.persistence.criteria.Root;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author admin
// */
//@Repository
//@Transactional
//public class StatsRepositoryImpl {
//
//    @Autowired
//    private LocalSessionFactoryBean factory;
//
//    public List<Object[]> statsRevenueByProduct() {
//        Session s = factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//
////            Root rP = q.from(Product.class); // Cách join mới sẽ không cần root pro
//        Root root = q.from(OrderDetail.class);
//        Join<OrderDetail, Product> join = root.join("productId", JoinType.RIGHT);
//        //join bên phải để lấy đc cả nhưng Product không có sum (quantity với unitPrice)
////            q.where(b.equal(rP.get("id"), rD.get("productId"))); //join mới không cần cái này
//
////nghĩa là join đang join là của Product(thằng được join tới)
//        q.multiselect(join.get("id"), join.get("name"), // select id vs name product
//                b.sum(b.prod(root.get("quantity"),
//                        root.get("unitPrice"))));
//        q.groupBy(join.get("id"));
//
//        Query query = s.createQuery(q);
//
//        return query.getResultList();
//    }
//
//    public List<Object[]> statsRevenuByTime(String time) {
//        Session s = factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root root = q.from(OrderDetail.class);
//        //Thằng này sẽ join qua kia qua gì ( thì thằng này sẽ join)
//        Join<OrderDetail, SaleOrder> join = root.join("orderId", JoinType.INNER);
//        q.multiselect(b.function(time, Integer.class, join.get("createdDate")),
//                b.sum(b.prod(root.get("quantity"), root.get("unitPrice"))));
//        q.groupBy(b.function(time, Integer.class, join.get("createdDate"))); // groupby chung time
//        Query query = s.createQuery(q);
//        return query.getResultList();
//    }
//}
