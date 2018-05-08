package demo.onlineshoppingbe;

import demo.onlineshoppingbe.config.HibernateConfig;
import demo.onlineshoppingbe.dao.CartLineDAO;
import demo.onlineshoppingbe.dao.ProductDAO;
import demo.onlineshoppingbe.dao.UserDAO;
import demo.onlineshoppingbe.dao.impl.CartLineDAOImpl;
import demo.onlineshoppingbe.dao.impl.ProductDAOImpl;
import demo.onlineshoppingbe.dao.impl.UserDAOImpl;
import demo.onlineshoppingbe.dto.Cart;
import demo.onlineshoppingbe.dto.CartLine;
import demo.onlineshoppingbe.dto.Product;
import demo.onlineshoppingbe.dto.User;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertTrue;

/**
 * @author b.erden
 * @created 08/05/2018
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
@Transactional
public class CartLineDAOTest {

    @Autowired
    private SessionFactory sessionFactory;
    private CartLineDAO cartLineDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    private CartLine cartLine;

    @Before
    public void init() {
        cartLineDAO = new CartLineDAOImpl(sessionFactory);
        productDAO = new ProductDAOImpl(sessionFactory);
        userDAO = new UserDAOImpl(sessionFactory);
    }

    @Test
    public void add_cart_line_successfully() {
        User userByEmail = userDAO.findUserByEmail("b@e.com");

        Cart userCart = userByEmail.getCart();

        Product product = productDAO.get(1); // getting one product

        cartLine = new CartLine();
        cartLine.setBuyingPrice(product.getUnitPrice());
        cartLine.setProductCount(cartLine.getProductCount() + 1);
        cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
        cartLine.setAvailable(true);
        cartLine.setCartId(userCart.getId());

        cartLine.setProduct(product);

        assertTrue(cartLineDAO.add(cartLine));

        userCart.setGrandTotal(userCart.getGrandTotal() + cartLine.getTotal());
        userCart.setCartLines(userCart.getCartLines() + 1);

        assertTrue(cartLineDAO.updateCart(userCart));
    }
}
