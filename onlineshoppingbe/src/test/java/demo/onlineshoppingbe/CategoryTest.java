package demo.onlineshoppingbe;

import demo.onlineshoppingbe.dao.CategoryDAO;
import demo.onlineshoppingbe.dto.Category;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */
public class CategoryTest {

    private static AnnotationConfigApplicationContext applicationContext;
    private static CategoryDAO categoryDAO;
    private Category category;

    @BeforeClass
    public static void init() {
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("demo.onlineshoppingbe");
        applicationContext.refresh();

        categoryDAO = (CategoryDAO) applicationContext.getBean("categoryDAOImpl");
    }

    @Test
    public void addTestCategory(){
        category = new Category();
        category.setDescription("Mobile Category Description");
        category.setName("Mobile");
        category.setImageUrl("MOBILE_CAT.png");

        assertEquals(true,categoryDAO.add(category));
    }
}
