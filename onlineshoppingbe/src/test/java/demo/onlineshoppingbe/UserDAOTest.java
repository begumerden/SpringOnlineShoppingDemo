package demo.onlineshoppingbe;

import demo.onlineshoppingbe.config.HibernateConfig;
import demo.onlineshoppingbe.dao.UserDAO;
import demo.onlineshoppingbe.dao.impl.UserDAOImpl;
import demo.onlineshoppingbe.dto.Address;
import demo.onlineshoppingbe.dto.Cart;
import demo.onlineshoppingbe.dto.User;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author: b.erden
 * @date: 27.4.2018
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
@Transactional
public class UserDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    private UserDAO userDAO;
    private User user;
    private Address address;

    @Before
    public void init() {
        userDAO = new UserDAOImpl(sessionFactory);
        user = new User();
        user.setFirstName("mycoolname");
        user.setLastName("mylastname");
        user.setEmail("testy@testmail.com");
        user.setContactNumber("123456789");
        user.setRole("USER");
        user.setPassword("password123");

        address = new Address();
        address.setAddressLineOne("first billing address line");
        address.setCountry("Turkey");
        address.setCity("Izmir");
        address.setBilling(true);
    }

    @Test
    public void add_operations_successful() {

        if (user.getRole().equals("USER")) {
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);

            assertTrue(userDAO.addUser(user));

            address.setUser(user);

            assertTrue(userDAO.addAddress(address));
        }
    }

    @Test
    public void find_by_email_should_successful() {
        userDAO.addUser(user);
        User userByEmail = userDAO.findUserByEmail("testy@testmail.com");

        assertNotNull(userByEmail);
    }

    @Test
    public void get_billing_address_should_successful(){
        userDAO.addUser(user);
        address.setUser(user);
        userDAO.addAddress(address);

        Address billingAddress = userDAO.getBillingAddress(user.getId());
        assertNotNull(billingAddress);
    }
}
