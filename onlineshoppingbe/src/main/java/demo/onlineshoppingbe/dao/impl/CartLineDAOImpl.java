package demo.onlineshoppingbe.dao.impl;

import demo.onlineshoppingbe.dao.CartLineDAO;
import demo.onlineshoppingbe.dto.Cart;
import demo.onlineshoppingbe.dto.CartLine;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author b.erden
 * @created 08/05/2018
 */

@Repository
@Transactional
public class CartLineDAOImpl implements CartLineDAO {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CartLineDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public CartLineDAOImpl(SessionFactory session) {
        this.sessionFactory = session;
    }

    @Override
    public CartLine getById(int id) {
        return sessionFactory.getCurrentSession().get(CartLine.class, id);
    }

    @Override
    public boolean add(CartLine cartLine) {
        try {
            sessionFactory.getCurrentSession().persist(cartLine);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(CartLine cartLine) {
        try {
            sessionFactory.getCurrentSession().update(cartLine);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(CartLine cartLine) {
        return false;
    }

    @Override
    public List<CartLine> list(int cartId) {
        String query = "FROM CartLine WHERE cartId = :cartId";
        return sessionFactory.getCurrentSession()
                             .createQuery(query)
                             .setParameter("cartId", cartId)
                             .list();
    }

    @Override
    public List<CartLine> listAvailable(int cartId) {
        String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
        return sessionFactory.getCurrentSession()
                             .createQuery(query)
                             .setParameter("cartId", cartId)
                             .setParameter("available", true)
                             .list();
    }

    @Override
    public CartLine getByCardAndProduct(int cartId, int productId) {
        String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
        CartLine result = (CartLine) sessionFactory.getCurrentSession()
                                                   .createQuery(query)
                                                   .setParameter("cartId", cartId)
                                                   .setParameter("productId", productId).uniqueResult();
        return result;

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
}
