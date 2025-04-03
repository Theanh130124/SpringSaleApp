
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
//Nap page_size -> rồi lấy thông qua biến env

@Repository
@PropertySource("classpath:configs.properties")
@Transactional
public class ProductRepositoryImpl implements ProductRepository{

    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    //đã có autowired tạo bao nhiêu lần vẫn là Sigleton ; 
    //Côi có thể độc env và PAGE_SIZE Thành danh sách biển tỉnh để đỡ phải ghi lại @PropertySource("classpath:configs.properties") ở nhiều nơi
    @Autowired
    private Environment env;
    
        //nạp bên properties
    

    @Override
    public List<Product> getProducts(Map<String, String> params) {
            Session s = factory.getObject().getCurrentSession();
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            Root root = q.from(Product.class);
            q.select(root);

            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();
                String kw = params.get("kw");
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
//cateId -> đúng tên với params trên web nhận từ @RequestParam
                String cateId = params.get("cateId");
                if (cateId != null && !cateId.isEmpty()) {
                    
                    //nữa sửa trong pojo thành category thay vì categoryId cũng đc
                    Predicate p4 = b.equal(root.get("categoryId").as(Integer.class), Integer.valueOf(cateId)); // khóa ngoại là đối tượng thì cần chỉ class Integer
                    predicates.add(p4);
                }

                q.where(predicates.toArray(Predicate[]::new));
            }

            Query query = s.createQuery(q);

            if (params != null) {
                
                String page = params.get("page");
                if (page != null && !page.isEmpty()) {
                    int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                    int p = Integer.parseInt(page);
                    int start = (p - 1) * pageSize;
                    
                    query.setFirstResult(start);
                    query.setMaxResults(pageSize);
                }
            }
            
            return query.getResultList();
        }

    @Override
    public Product addOrUpdate(Product p) {
              Session s = this.factory.getObject().getCurrentSession();
             if (p.getId() == null)
                 s.persist(p);
             else
                 s.merge(p);
         
         
         return p;
        
    }

    @Override
    public Product getProductById(int id) {
        Session s = factory.getObject().getCurrentSession();
            return s.get(Product.class, id);
        
    }

    @Override
    public int countProduct() {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class); // nó yêu cầu trả Long count á
        Root pro = q.from(Product.class);
        q.select(b.count(pro)); //count nó yêu cầu trả long
        Query query = s.createQuery(q);//tạo truy vấn
         return ((Long) query.getSingleResult()).intValue(); // trả về số sản phẩm
    }

    @Override
    public void deleteProduct(int id) {
       Session s = factory.getObject().getCurrentSession();
        Product p = this.getProductById(id);
        s.remove(p);
    }
}
