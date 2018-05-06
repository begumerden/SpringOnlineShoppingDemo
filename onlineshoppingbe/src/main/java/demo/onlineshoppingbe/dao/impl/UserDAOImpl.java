package demo.onlineshoppingbe.dao.impl;

import demo.onlineshoppingbe.dao.UserDAO;
import demo.onlineshoppingbe.dto.Address;
import demo.onlineshoppingbe.dto.Cart;
import demo.onlineshoppingbe.dto.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: b.erden
 * @date: 27.4.2018
 */

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {
        checkNotNull(user);

        return add(user);
    }

    @Override
    public User findUserByEmail(String email) {
        checkArgument(StringUtils.isNotBlank(email));

        String sql = "FROM user_info WHERE email = :email";

        try {
            return (User) sessionFactory.getCurrentSession()
                    .createQuery(sql)
                    .setParameter("email", email).uniqueResult();
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return null;
    }

    @Override
    public boolean addAddress(Address address) {
        return add(address);
    }

    @Override
    public Address getBillingAddress(int userId) {
        String query = "FROM Address WHERE user.id = :user and billing = :billing";

        try {
            return (Address) sessionFactory.getCurrentSession().createQuery(query)
                    .setParameter("user", userId).setParameter("billing",true).uniqueResult();
        } catch (Exception e) {
            logger.error("Error", e);
            throw e;
        }
    }

    @Override
    public List<Address> listShippingAddressesByUserId(int userId) {

        String query = "FROM Address WHERE user.id = :user and shipping = :shipping";
        try {
            return sessionFactory.getCurrentSession().createQuery(query)
                    .setParameter("user", userId)
                    .setParameter("shipping",true).list();
        } catch (Exception e) {
            logger.error("Error", e);
            throw e;
        }
    }

    @Override
    public boolean updateCart(Cart cart) {
        try {
            sessionFactory.getCurrentSession().update(cart);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred", e);
            return false;
        }
    }


    private boolean add(Object o) {
        try {
            sessionFactory.getCurrentSession().persist(o);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred", e);
            return false;
        }
    }
}
