package demo.onlineshoppingbe.dao.impl;

import demo.onlineshoppingbe.dto.Category;
import demo.onlineshoppingbe.dao.CategoryDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        try {
            sessionFactory.getCurrentSession().update(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Category category) {
        category.setActive(false);
        return update(category);
    }

}
