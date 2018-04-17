package demo.onlineshoppingbe;

import demo.onlineshoppingbe.config.HibernateConfig;
import demo.onlineshoppingbe.dao.CategoryDAO;
import demo.onlineshoppingbe.dao.impl.CategoryDAOImpl;
import demo.onlineshoppingbe.dto.Category;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
@Transactional
public class CategoryTest {

    private CategoryDAO categoryDAO;
    @Autowired
    private SessionFactory sessionFactory;
    private Category category;


    @Before
    public void init() {
        categoryDAO = new CategoryDAOImpl(sessionFactory);
    }

    @Test
    public void add_category_should_successful() {
        category = new Category();
        category.setDescription("Mobile Category Description");
        category.setName("Mobile");
        category.setImageUrl("MOBILE_CAT.png");

        assertTrue(categoryDAO.add(category));
    }

    @Test
    public void get_category_should_successful() {
        Category category = categoryDAO.getById(1);
        assertNotNull(category);
    }

    @Test
    public void update_should_successful() {
        Category category = categoryDAO.getById(1);
        category.setName("Updated");

        assertTrue(categoryDAO.update(category));
        assertTrue(categoryDAO.getById(1).getName().equals("Updated"));
    }

    @Test
    public void delete_category_should_successful() {
        Category category = categoryDAO.getById(1);

        assertTrue(categoryDAO.delete(category));
        assertFalse(categoryDAO.getById(1).isActive());
    }

    @Test
    public void list_category_should_successful() {
        List<Category> categories = categoryDAO.list();
        assertNotNull(categories);
        assertTrue(categories.size() > 0 );
    }
}
