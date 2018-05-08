package demo.onlineshoppingbe.dao.impl;

import demo.onlineshoppingbe.dao.CategoryDAO;
import demo.onlineshoppingbe.dto.Category;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */
@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory session) {
        this.sessionFactory = session;
    }

    @Override
    public List<Category> list() {
        String hql = "FROM Category where active = :active";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("active",true);
        return query.list();
    }

    @Override
    public Category getById(int id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public boolean add(Category category) {
        try {
            sessionFactory.getCurrentSession().persist(category);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred", e);
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        try {
            sessionFactory.getCurrentSession().update(category);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred", e);
        }
        return false;
    }

    @Override
    public boolean delete(Category category) {
        category.setActive(false);
        return update(category);
    }

}
