
package com.trantheanh1301.repository.impl;



import com.trantheanh1301.pojo.Product;
import com.trantheanh1301.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository{
    private static final int PAGE_SIZE = 4;
    
    @Autowired
    private LocalSessionFactoryBean factory;
    


    @Override
    public List<Product> getProducts(Map<String, String> params) {
            Session s = factory.getObject().getCurrentSession();
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            Root root = q.from(Product.class);
            q.select(root);

            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();
                String kw = params.get("q");
                if (kw != null && !kw.isEmpty()) {
                    Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                    predicates.add(p1);
                }

                String fromPrice = params.get("fromPrice");
                if (fromPrice != null && !fromPrice.isEmpty()) {
                    Predicate p2 = b.greaterThanOrEqualTo(root.get("price"), Double.valueOf(fromPrice));
                    predicates.add(p2);
                }

                String toPrice = params.get("toPrice");
                if (toPrice != null && !toPrice.isEmpty()) {
                    Predicate p3 = b.lessThanOrEqualTo(root.get("price"), Double.valueOf(toPrice));
                    predicates.add(p3);
                }

                String cateId = params.get("cateId");
                if (cateId != null && !cateId.isEmpty()) {
                    Predicate p4 = b.equal(root.get("category"), Integer.valueOf(cateId));
                    predicates.add(p4);
                }

                q.where(predicates.toArray(Predicate[]::new));
            }

            Query query = s.createQuery(q);

            if (params != null) {
                String page = params.get("page");
                if (page != null && !page.isEmpty()) {
                    int p = Integer.parseInt(page);
                    int start = (p - 1) * PAGE_SIZE;
                    
                    query.setFirstResult(start);
                    query.setMaxResults(PAGE_SIZE);
                }
            }
            
            return query.getResultList();
        }

    @Override
    public void addOrUpdate(Product p) {
            Session s = factory.getObject().getCurrentSession();
            if (p.getId() != null)
                s.update(s);
            else
                s.save(s);
        
    }

    @Override
    public Product getProductById(int id) {
        Session s = factory.getObject().getCurrentSession();
            return s.get(Product.class, id);
        
    }
}
