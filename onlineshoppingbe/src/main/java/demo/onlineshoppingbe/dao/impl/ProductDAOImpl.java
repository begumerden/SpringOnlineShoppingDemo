package demo.onlineshoppingbe.dao.impl;

import demo.onlineshoppingbe.dao.ProductDAO;
import demo.onlineshoppingbe.dto.Product;
import org.hibernate.SessionFactory;
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {
            sessionFactory.getCurrentSession().update(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
}
