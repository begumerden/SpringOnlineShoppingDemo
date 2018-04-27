package demo.onlineshoppingbe;

import demo.onlineshoppingbe.config.HibernateConfig;
import demo.onlineshoppingbe.dao.ProductDAO;
import demo.onlineshoppingbe.dao.impl.ProductDAOImpl;
import demo.onlineshoppingbe.dto.Product;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author: b.erden
 * @date: 18.4.2018
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
@Transactional
public class ProductDAOTest {

    private ProductDAO productDAO;
    @Autowired
    private SessionFactory sessionFactory;
    private Product product;

    @Before
    public void init() {
        productDAO = new ProductDAOImpl(sessionFactory);
    }


    @Test
    public void crud_product_should_successful() {

        // create operation
        product = new Product();

        product.setName("MobileProductName");
        product.setBrand("brand");
        product.setDescription("This is some description");
        product.setUnitPrice(10000);
        product.setActive(true);
        product.setCategoryId(1);
        product.setSupplierId(2);

        assertTrue(productDAO.add(product));

        // reading and updating the category
        product = productDAO.get(2);
        product.setName("Samsung Galaxy S7");
        assertTrue(productDAO.update(product));

        assertTrue(productDAO.delete(product));
        assertFalse(productDAO.get(2).isActive());

        // list
        assertEquals(6, productDAO.list().size());
    }


    @Test
    public void list_active_products_should_successful() {
        assertNotNull(productDAO.listActiveProducts());
    }


    @Test
    public void list_active_product_by_category_should_successful() {
        assertEquals(1, productDAO.listActiveProductsByCategory(3).size());
        assertEquals(3, productDAO.listActiveProductsByCategory(1).size());
    }

}
