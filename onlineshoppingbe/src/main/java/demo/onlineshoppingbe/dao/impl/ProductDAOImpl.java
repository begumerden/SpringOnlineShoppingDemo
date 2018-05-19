package demo.onlineshoppingbe.dao.impl;

import demo.onlineshoppingbe.dao.ProductDAO;
import demo.onlineshoppingbe.dto.Product;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: b.erden
 * @date: 18.4.2018
 */

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product get(int productId) {
        try {
            return sessionFactory.getCurrentSession().get(Product.class, productId);
        } catch (Exception e) {
            logger.error("Error occurred", e);
        }
        return null;
    }

    @Override
    public List<Product> list() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM Product")
                .list();
    }

    @Override
    public boolean add(Product product) {
        try {
            sessionFactory.getCurrentSession().persist(product);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getStackTrace());
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {
            sessionFactory.getCurrentSession().update(product);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred", e);
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        product.setActive(false);
        return update(product);
    }

    @Override
    public List<Product> listActiveProducts() {
        String hql = "FROM Product WHERE active = :active";
        return sessionFactory.getCurrentSession()
                .createQuery(hql).setParameter("active", true).list();
    }

    @Override
    public List<Product> listActiveProductsByCategory(int categoryId) {
        String hql = "FROM Product WHERE active = :active and categoryId = :categoryId";
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("active", true)
                .setParameter("categoryId", categoryId).list();
    }

    @Override
    public List<Product> listProductsByViewCount() {
        String hql = "FROM Product WHERE active = :active ORDER BY views";
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("active", true).list();
    }


}
