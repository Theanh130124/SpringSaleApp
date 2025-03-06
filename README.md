# SpringSaleApp
DemoSpringMVC


    
    public void addOrUpdateProduct(Product p) {
        try (Session s = hibernationUtils.getFACTORY().openSession()) {

            s.getTransaction().begin();
            if (p.getId() != null) {
                s.merge(p);

            } else {
                s.persist(p);
            }
            s.getTransaction().commit();

        }

    }


                if(params!=null){
               String namePro = params.get("namePro");
                if (namePro != null && !namePro.isEmpty()) {

                    predicates.add(builder.like(rP.get("name").as(String.class), String.format("%%%s%%", namePro)));

                } 
            }










NEW JOIN 


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trantheanh1301.repository.impl;

import com.trantheanh1301.hibernate.hibernationUtils;
import com.trantheanh1301.pojo.Category;
import com.trantheanh1301.pojo.OrderDetail;
import com.trantheanh1301.pojo.Product;
import com.trantheanh1301.pojo.SaleOrder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class StatsRepository {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public List<Object[]> stats(Map<String, String> params) throws ParseException {

        try (Session session = hibernationUtils.getFACTORY().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<Product> rP = query.from(Product.class);
            Root<OrderDetail> rO = query.from(OrderDetail.class);
            Root<SaleOrder> rS = query.from(SaleOrder.class);
            query.multiselect(rP.get("id"), rP.get("name"), builder.sum(builder.prod(rO.get("unitPrice"), rO.get("quantity"))));

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(rO.get("productId").as(Integer.TYPE), rP.get("id")));
            predicates.add(builder.equal(rO.get("orderId").as(Integer.class), rS.get("id")));


            

            if (params != null) {
                String namePro = params.get("namePro");
                if (namePro != null && !namePro.isEmpty()) {

                    predicates.add(builder.like(rP.get("name").as(String.class), String.format("%%%s%%", namePro)));

                }
            }

            if (params != null) {

                String fd = params.get("fromDate");
                String td = params.get("toDate");

                if (fd != null && !fd.isEmpty()) {

                    predicates.add(builder.greaterThanOrEqualTo(rS.get("createdDate"), FORMAT.parse(fd)));
                }
                if (td != null && !td.isEmpty()) {

                    predicates.add(builder.lessThanOrEqualTo(rS.get("createdDate"), FORMAT.parse(td)));
                }

            }

            String quarter = params.get("quarter");
           
            if (quarter != null && !quarter.isEmpty()) {
                String year = params.get("year");
                if (year != null && !year.isEmpty()) {
                    predicates.addAll(Arrays.asList(
                            builder.equal(builder.function("YEAR", Integer.class, rS.get("createdDate")), Integer.valueOf(year)),
                            builder.equal(builder.function("QUARTER", Integer.class, rS.get("createdDate")), Integer.valueOf(quarter))
                    ));

                }
            }
            

            
            query.where(predicates.toArray(Predicate[]::new));
            query.groupBy(rP.get("id"));

            Query q = session.createQuery(query);
            return q.getResultList();

        }

    }
}






//JOIN NEW
//            Join<OrderDetail,Product> join = rO.join("productId",JoinType.RIGHT);
//            Join<OrderDetail,SaleOrder> join_2 = rO.join("orderId",JoinType.INNER);
//            predicates.add(join);
////            predicates.add(join_2)
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
//        Root<Country> root = criteria.from(Country.class);
//        Join<Country, Translation> join = root.join("translatedName");
//        criteria.multiselect(root, join.get("fr"));
//        TypedQuery<Country> tq = session.createQuery(criteria);
//        List<Country> countries = tq.getResultList();
